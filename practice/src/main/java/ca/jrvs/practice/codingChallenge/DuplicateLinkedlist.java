package ca.jrvs.practice.codingChallenge;

import java.util.Collections;
import java.util.LinkedList;

public class DuplicateLinkedlist {




  public static void main(String[] args) {
    LinkedList<Integer> linkedList= new LinkedList<>();
    linkedList.add(1);
    linkedList.add(3);
    linkedList.add(5);
    linkedList.add(3);
    linkedList.add(5);
    linkedList.add(1);
    System.out.println("Before removing duplicates " +linkedList);
    RemoveDupli(linkedList);
    System.out.println("After removing duplciated "+linkedList);
  }

public static void RemoveDupli(LinkedList<Integer> lkl){
  Collections.sort(lkl);
  for(int i=0 ; i<lkl.size();i++){
    if(lkl.get(i)==lkl.get(i+1)){
      lkl.remove(i+1);
    }
  }


}
}
