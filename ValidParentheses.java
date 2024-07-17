import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else {
                return false; // either the stack is empty or brackets do not match
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();

        System.out.println(vp.isValid("()")); // true
        System.out.println(vp.isValid("()[]{}")); // true
        System.out.println(vp.isValid("(]")); // false
        System.out.println(vp.isValid("([)]")); // false
        System.out.println(vp.isValid("{[]}")); // true
    }
}
