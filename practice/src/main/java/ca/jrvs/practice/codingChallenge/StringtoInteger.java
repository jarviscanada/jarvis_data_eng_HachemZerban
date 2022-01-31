package ca.jrvs.practice.codingChallenge;

import java.util.Calendar;

public class StringtoInteger {

  public static void main(String[] args) {
    String str1 = "oo - 001 a 897 ";

    System.out.println(stringtoInt(str1));


  }

public static int stringtoInt(String str) {
    boolean negative =false;

    int sum =0 ;
    for(int i=0;i<str.length();i++) {

       if  ((int)str.charAt(i)==45) {
         negative = true;

       }
      if (Character.isDigit(str.charAt(i))) {
        sum =sum*10+(str.charAt(i) - '0');

      }

    }
if(negative)
  return -sum ;
else
  return sum;

}
}
