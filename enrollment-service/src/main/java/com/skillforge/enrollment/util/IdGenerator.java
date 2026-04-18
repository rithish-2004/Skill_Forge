package com.skillforge.enrollment.util;

import java.security.SecureRandom;

public final class IdGenerator {
  private static final SecureRandom RANDOM = new SecureRandom();

  private IdGenerator() {}

  public static String nextEnrollmentId() {
    return "ENR" + digits(10);
  }

  public static String nextAttendanceId() {
    return "ATT" + digits(10);
  }

  private static String digits(int len) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      sb.append(RANDOM.nextInt(10));
    }
    return sb.toString();
  }
}
