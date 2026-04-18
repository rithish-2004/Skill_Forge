package com.skillforge.assessment.util;

import java.security.SecureRandom;

public final class IdGenerator {
  private static final SecureRandom RANDOM = new SecureRandom();

  private IdGenerator() {}

  public static String nextAssessmentId() {
    return "ASM" + digits(10);
  }

  public static String nextResultId() {
    return "RES" + digits(10);
  }

  public static String nextCertificationId() {
    return "CERT" + digits(10);
  }

  private static String digits(int len) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      sb.append(RANDOM.nextInt(10));
    }
    return sb.toString();
  }
}
