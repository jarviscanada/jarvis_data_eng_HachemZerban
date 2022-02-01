package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {

  public static void main(String[] args) {

    System.out.println(validAnagraMap("race", "care"));

  }

  public static boolean validAnagramSort(String s, String t) {
    char[] char1 = s.toCharArray();
    char[] char2 = t.toCharArray();
    Arrays.sort(char1);
    Arrays.sort(char2);

    return String.valueOf(char1).equals(String.valueOf(char2));


  }

  public static boolean validAnagraMap(String s, String t) {

    HashMap<String, Integer> hashMap1 = new HashMap<String, Integer>();
    HashMap<String, Integer> hashMap2 = new HashMap<String, Integer>();
    for (int i = 0; i < s.length(); i++) {

      if (!hashMap1.containsKey(Character.toString(s.charAt(i)))) {
        hashMap1.put(Character.toString(s.charAt(i)), 1);
      } else {
        Integer oldvalone = new Integer(hashMap1.get(Character.toString(s.charAt(i))));
        hashMap1.replace(Character.toString(s.charAt(i)), oldvalone + 1);
      }

      if (!hashMap2.containsKey(Character.toString(t.charAt(i)))) {
        hashMap2.put(Character.toString(t.charAt(i)), 1);
      } else {
        Integer oldvaltwo = new Integer(hashMap2.get(Character.toString(t.charAt(i))));
        hashMap1.replace(Character.toString(s.charAt(i)), oldvaltwo + 1);
      }
    }

    return hashMap1.equals(hashMap2);
  }
}
