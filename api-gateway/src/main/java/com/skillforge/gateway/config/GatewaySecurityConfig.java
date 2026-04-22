package com.skillforge.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {
  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    http.csrf(ServerHttpSecurity.CsrfSpec::disable)
        .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
        .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
        .authorizeExchange(ex -> ex.anyExchange().permitAll());
    return http.build();
  }

  @Bean
  @Order(-1)
  public WebFilter gatewayJwtFilter(com.skillforge.gateway.util.JwtUtil jwtUtil) {
    return (ServerWebExchange exchange, WebFilterChain chain) -> {
      var path = exchange.getRequest().getURI().getPath();
      if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
        return chain.filter(exchange);
      }
      if (path.startsWith("/api/v1/auth/login")
          || path.startsWith("/api/v1/auth/register")
          || path.startsWith("/actuator")) {
        return chain.filter(exchange);
      }
      var auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
      if (auth == null || !auth.startsWith("Bearer ")) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
      }
      var token = auth.substring(7);
      if (!jwtUtil.valid(token)) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
      }
      var claims = jwtUtil.parse(token);
      var userId = claims.getSubject();
      var role = claims.get("role", String.class);
      var mutated =
          exchange
              .mutate()
              .request(
                  r ->
                      r.headers(
                              h -> {
                                h.set("X-User-Id", userId);
                                if (role != null) {
                                  h.set("X-User-Role", role);
                                }
                              })
                          .build())
              .build();
      return chain.filter(mutated);
    };
  }
}
