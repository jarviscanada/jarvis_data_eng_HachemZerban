package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class QueuetwoStacks {

  Stack<Integer> stack1;
  Stack<Integer> stack2;

  QueuetwoStacks() {
    stack1 = new Stack<Integer>();
    stack2 = new Stack<Integer>();

  }

  public void push(int e) {

    stack1.push(e);


  }


  public int pop() {
    if (stack1.empty()) {
      throw new RuntimeException("Stack is empty ");
    }
    int size = stack1.size();
    for (int j = 0; j < size; j++) {
      stack2.push(stack1.pop());

    }
    return stack2.pop();
  }
}
