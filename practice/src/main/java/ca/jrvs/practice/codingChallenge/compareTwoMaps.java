package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class compareTwoMaps {

  public static void main(String[] args) {
    Map<Integer, Integer> map1 = new HashMap<>();
    Map<Integer, Integer> map2 = new HashMap<>();
    map1.put(1, 1);
    map1.put(2, 2);
    map2.put(23, 2);
    map2.put(12, 1);

    System.out.println(compareMapsValue(map1, map2));


  }

  public static boolean compareMaps(Map<?, ?> m1, Map<?, ?> m2) {

    return m1.equals(m2);

  }

  public static boolean compareMapsValue(Map<?, ?> m1, Map<?, ?> m2) {
    ArrayList arr1 = new ArrayList<>(m1.values());
    ArrayList arr2 = new ArrayList<>(m2.values());
    return arr1.equals(arr2);


  }
}
