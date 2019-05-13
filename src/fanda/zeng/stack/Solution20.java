package fanda.zeng.stack;

/**
 * @Description: LeetCode 20号题目（有效的括号）
 * @Author: fanda
 * @Date: 2019/5/13
 */
public class Solution20 {

    private ArrayStack<Character> stack = new ArrayStack<>();

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     */
    public boolean isValid(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果是左侧括号，则入栈
            if (c == ('(') || c == ('[') || c == ('{')) {
                stack.push(s.charAt(i));
            } else {
                // 如果是右侧括号，但是此时栈内为空，则返回 false
                if (stack.isEmpty()) {
                    return false;
                } else {
                    // 将栈顶括号和当前括号比较，如果左右括号闭合，则出栈，然后返回 false
                    char topChar = stack.peek();
                    if (topChar == ('(') && c == (')') || topChar == ('[') && c == (']') || topChar == ('{') && c == ('}')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        // 最后，如果栈内元素为空，则返回 true ，否则返回 false
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution20().isValid("{}([])"));
    }
}
