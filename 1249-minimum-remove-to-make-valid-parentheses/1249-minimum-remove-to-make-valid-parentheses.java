class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int open = 0;

        // Forward pass: remove invalid ')'
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
                sb.append(c);
            } else if (c == ')') {
                if (open > 0) {
                    open--;
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }

        // Backward pass: remove extra '('
        StringBuilder result = new StringBuilder();
        int close = 0;
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == ')') {
                close++;
                result.append(c);
            } else if (c == '(') {
                if (close > 0) {
                    close--;
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }

        return result.reverse().toString();
    }
}
