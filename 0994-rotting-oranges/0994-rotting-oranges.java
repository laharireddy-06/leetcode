import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        // Step 1: Add all rotten oranges to queue, count fresh
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // If no fresh oranges, answer is 0
        if (fresh == 0) return 0;

        int minutes = -1;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        // Step 2: BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++;
            for (int k = 0; k < size; k++) {
                int[] cell = queue.poll();
                for (int[] d : dirs) {
                    int x = cell[0] + d[0];
                    int y = cell[1] + d[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2; // rot it
                        fresh--;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }

        return fresh == 0 ? minutes : -1;
    }
}
