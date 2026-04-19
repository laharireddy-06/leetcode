import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<List<Integer>> redGraph = new ArrayList<>();
        List<List<Integer>> blueGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            redGraph.add(new ArrayList<>());
            blueGraph.add(new ArrayList<>());
        }
        for (int[] e : redEdges) redGraph.get(e[0]).add(e[1]);
        for (int[] e : blueEdges) blueGraph.get(e[0]).add(e[1]);

        int[][] dist = new int[2][n]; // 0=red, 1=blue
        Arrays.fill(dist[0], Integer.MAX_VALUE);
        Arrays.fill(dist[1], Integer.MAX_VALUE);
        dist[0][0] = dist[1][0] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); // node 0, last edge red
        queue.offer(new int[]{0, 1}); // node 0, last edge blue

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0], color = cur[1];
            List<List<Integer>> nextGraph = (color == 0) ? blueGraph : redGraph;
            int nextColor = 1 - color;
            for (int nei : nextGraph.get(node)) {
                if (dist[nextColor][nei] == Integer.MAX_VALUE) {
                    dist[nextColor][nei] = dist[color][node] + 1;
                    queue.offer(new int[]{nei, nextColor});
                }
            }
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int d = Math.min(dist[0][i], dist[1][i]);
            answer[i] = (d == Integer.MAX_VALUE) ? -1 : d;
        }
        return answer;
    }
}
