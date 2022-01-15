 /**
 * Java Stack Efficient Pass
 * Status: Accepted
 * 62 / 62 test cases passed.
 * Runtime: 25 ms
 * Memory Usage: 48.8 MB
 * Details:
 * - Your runtime beats 39.48 % of java submissions.
 * - Your memory usage beats 24.54 % of java submissions.
*/

public String minRemoveToMakeValid(String s) {
    StringBuilder sb = new StringBuilder(s);
    Stack<Integer> st = new Stack();
    int i = 0;
    while (i < sb.length()) {
        if (sb.charAt(i) == '(') st.add(i);
        if (sb.charAt(i) == ')') {
            if (!st.empty() && st.peek() >= 0) st.pop();
            else {
                sb.deleteCharAt(i);
                continue;
            }
        }
        i++;
    }
    while (!st.empty())
        sb.deleteCharAt(st.pop());
    return sb.toString();
}