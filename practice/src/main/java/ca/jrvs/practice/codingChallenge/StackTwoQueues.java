package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;


public class StackTwoQueues {

  Queue<Integer> queue1;
  Queue<Integer> queue2;

  StackTwoQueues() {
    queue1 = new LinkedList<Integer>();
    queue2 = new LinkedList<Integer>();

  }

  // Q1: 50,20
  // Q2:  20
  public void push(int e) {
    if (queue1.size() == 0) {
      queue1.add(e);
    } else {
      int sizeOfQueue1 = queue1.size();
      for (int i = 0; i < sizeOfQueue1; i++) {
        queue2.add(queue1.remove());
        queue1.add(e);
      }
      for (int j = 0; j < sizeOfQueue1; j++) {
        queue1.add(queue2.remove());
      }
    }


  }

  public int pop() {

    if (queue1.size() == 0) {
      throw new RuntimeException("Queue is empty");
    }
    return queue1.remove();
  }

}



