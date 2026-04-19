class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];

        // Count trust relationships
        for (int[] relation : trust) {
            int a = relation[0];
            int b = relation[1];
            outdegree[a]++;
            indegree[b]++;
        }

        // Judge must have indegree = n - 1 and outdegree = 0
        for (int person = 1; person <= n; person++) {
            if (indegree[person] == n - 1 && outdegree[person] == 0) {
                return person;
            }
        }
        return -1;
    }
}
