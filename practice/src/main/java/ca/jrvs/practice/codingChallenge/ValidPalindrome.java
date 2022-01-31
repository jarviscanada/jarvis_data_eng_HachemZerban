package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class ValidPalindrome {

  public static void main(String[] args) {

    String str = "noon";
   // System.out.println(checkPlalindRecu(str));
    System.out.println(checkPalindStack(str));
  }

  public static boolean checkPalindStack(String str){
    Stack<Character> stack = new Stack<Character>();
    for(int i=0 ; i<str.length();i++){
      stack.push(str.charAt(i)) ;

    }
    for (int j=0 ; j<str.length(); j++){
      if(str.charAt(j)!=stack.pop()){
        return false ;

      }

    }
return true ;

  }
  public static boolean checkPlalindRecu(String str) {

    int i = 0 ;
     if (str.isEmpty()) {return true ;}

      if (str.charAt(i) == str.charAt(str.length() - 1)) {
        String tempor = str.substring(i + 1, str.length() - 1);
      return  checkPlalindRecu(tempor);

      } else {
        return false;
      }



  }
}
