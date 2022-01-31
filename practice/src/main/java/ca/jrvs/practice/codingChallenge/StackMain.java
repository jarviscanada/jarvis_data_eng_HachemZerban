package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.StackTwoQueues;

public class StackMain {

  public static void main(String[] args) {
    StackTwoQueues stack = new StackTwoQueues();
    stack.push(20);
    stack.push(40);
    stack.push(70);

    System.out.println("Removed element : "+ stack.pop());
    stack.push(170);
    System.out.println("Removed element : "+ stack.pop());
    // Implementation of one stack
    StackOneQueues stack1= new StackOneQueues();
    stack1.push(4);
    stack1.push(9);
    stack1.push(10);
    System.out.println("Top value :"+stack1.top());
    stack1.push(404);
    System.out.println("Top value :"+stack1.top());
  }

}
