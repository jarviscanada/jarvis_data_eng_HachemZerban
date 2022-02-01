package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSums {

  public static void main(String[] args) {
    int[] arr = {2, 7, 9, 13, 15, 28};
    int[] res = checkSum(arr, 9);

    System.out.println(Arrays.toString(res));

  }

  public static int[] checkSum(int[] arr, int target) {
    int index = 0;
    int[] result = new int[2];
    HashMap<Integer, Integer> map1 = new HashMap<>();
    for (int element : arr) {
      if (!map1.containsKey(target - element)) {
        map1.put(element, index);
        index++;

      } else {
        result[0] = index;
        result[1] = element;


      }

    }

    return result;

  }


}
