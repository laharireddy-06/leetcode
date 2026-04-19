import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // Step 1: Assign unique group IDs to items with group = -1
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        // Step 2: Build graphs
        List<List<Integer>> itemGraph = new ArrayList<>();
        int[] itemIndegree = new int[n];
        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());

        List<List<Integer>> groupGraph = new ArrayList<>();
        int[] groupIndegree = new int[m];
        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            for (int pre : beforeItems.get(i)) {
                itemGraph.get(pre).add(i);
                itemIndegree[i]++;
                if (group[i] != group[pre]) {
                    groupGraph.get(group[pre]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // Step 3: Topological sort for items and groups
        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);

        if (itemOrder.isEmpty() || groupOrder.isEmpty()) return new int[0];

        // Step 4: Group items by group
        Map<Integer, List<Integer>> groupedItems = new HashMap<>();
        for (int item : itemOrder) {
            groupedItems.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        // Step 5: Build final order using group order
        List<Integer> result = new ArrayList<>();
        for (int g : groupOrder) {
            if (groupedItems.containsKey(g)) {
                result.addAll(groupedItems.get(g));
            }
        }

        // Convert List<Integer> to int[]
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int size) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for (int nei : graph.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0) queue.offer(nei);
            }
        }
        return order.size() == size ? order : new ArrayList<>();
    }
}
