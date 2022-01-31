package ca.jrvs.practice.codingChallenge;

public class QueueMain {

  public static void main(String[] args) {
    QueuetwoStacks queue = new QueuetwoStacks();

    queue.push(34);
    queue.push(7);
    queue.push(83);
    queue.push(3094);

    System.out.println(queue.pop());

  }


}
