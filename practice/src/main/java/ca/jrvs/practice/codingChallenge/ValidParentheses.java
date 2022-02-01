package ca.jrvs.practice.codingChallenge;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

  public static void main(String[] args) {
    String str = "(())]";
    System.out.println(checkParenthese(str));
  }

  public static boolean checkParenthese(String str) {
    Stack<Character> stack = new Stack<>();
    Map<Character, Character> hashmap = new HashMap<Character, Character>();
    hashmap.put('(', ')');
    hashmap.put('[', ']');
    hashmap.put('{', '}');
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (hashmap.containsKey(ch)) {
        stack.push(ch);

      } else if (!stack.empty() && ch == hashmap.get(stack.peek())) {
        stack.pop();

      } else {
        return false;
      }
    }
    return stack.empty();
  }

}
