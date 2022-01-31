package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class StackOneQueues {

  Queue<Integer> queue= new LinkedList<Integer>();

  public void push(int val) {
    int size = queue.size();

    queue.add(val);
    for (int i=0; i<size;i++) {
      int topval = queue.remove();
      queue.add(topval);

    }

  }
  public int pop(){
    if(queue.isEmpty()){
      System.out.println("is empty");
      return -1 ;
    }
    return  queue.remove();


  }
 public int top(){
    if(queue.isEmpty())
      return -1;
    return queue.peek();

 }

}
