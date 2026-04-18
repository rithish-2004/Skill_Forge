package com.skillforge.identity.util;

import com.skillforge.identity.entity.UserRole;
import java.security.SecureRandom;

public final class IdGenerator {
  private static final SecureRandom RANDOM = new SecureRandom();

  private IdGenerator() {}

  public static String nextUserId(UserRole role) {
    String prefix =
        switch (role) {
          case EMPLOYEE -> "EMP";
          case TRAINER -> "TRN";
          case ADMIN -> "ADM";
          case HR -> "HR";
        };
    return prefix + digits(10);
  }

  public static String nextAuditLogId() {
    return "ALG" + digits(10);
  }

  private static String digits(int len) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      sb.append(RANDOM.nextInt(10));
    }
    return sb.toString();
  }
}
