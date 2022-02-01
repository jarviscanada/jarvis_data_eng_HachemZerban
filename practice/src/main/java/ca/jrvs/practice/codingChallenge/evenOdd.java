package ca.jrvs.practice.codingChallenge;

public class evenOdd {

  public static void main(String[] args) {
    System.out.println(checkoddEven(9));

  }

  public static String checkoddEven(int n) {

    return n % 2 == 0 ? "odd" : "even";


  }
}
