class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int a : asteroids) {
            boolean alive = true;
            while (alive && !stack.isEmpty() && stack.peek() > 0 && a < 0) {
                if (stack.peek() < -a) {
                    stack.pop(); // top asteroid explodes
                    continue;
                } else if (stack.peek() == -a) {
                    stack.pop(); // both explode
                }
                alive = false; // current asteroid explodes
            }
            if (alive) {
                stack.push(a);
            }
        }
        // convert stack to array
        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }
}
