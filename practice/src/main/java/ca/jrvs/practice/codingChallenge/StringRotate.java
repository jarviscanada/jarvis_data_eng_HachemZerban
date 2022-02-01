package ca.jrvs.practice.codingChallenge;

public class StringRotate {

  public static void main(String[] args) {
    String sn = "abcde";
    System.out.println(rotateStrTwo(sn, "eabcd"));
  }

  public static boolean rotateStrOne(String str, String goal) {
    if (str.length() != goal.length()) {
      return false;
    }

    for (int i = 0; i < str.length(); i++) {
      StringBuilder nf = new StringBuilder(str.substring(i + 1));
      nf.append(str, 0, i + 1);
      if (nf.toString().equals(goal)) {
        return true;
      }

    }
    return false;
  }

  public static boolean rotateStrTwo(String str, String goal) {
    return ((str.length() == goal.length()) && (str + str).contains(goal));

  }
}
