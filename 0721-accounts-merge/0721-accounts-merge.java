import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, String> parent = new HashMap<>();

        // Step 1: Initialize Union-Find
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                emailToName.put(email, name);
                parent.put(email, email);
            }
        }

        // Step 2: Union emails in the same account
        for (List<String> acc : accounts) {
            String firstEmail = acc.get(1);
            for (int i = 2; i < acc.size(); i++) {
                union(parent, firstEmail, acc.get(i));
            }
        }

        // Step 3: Group emails by root parent
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (String email : parent.keySet()) {
            String root = find(parent, email);
            unions.computeIfAbsent(root, k -> new TreeSet<>()).add(email);
        }

        // Step 4: Build result
        List<List<String>> result = new ArrayList<>();
        for (String root : unions.keySet()) {
            List<String> merged = new ArrayList<>();
            merged.add(emailToName.get(root));
            merged.addAll(unions.get(root));
            result.add(merged);
        }
        return result;
    }

    private String find(Map<String, String> parent, String email) {
        if (!parent.get(email).equals(email)) {
            parent.put(email, find(parent, parent.get(email)));
        }
        return parent.get(email);
    }

    private void union(Map<String, String> parent, String a, String b) {
        String rootA = find(parent, a);
        String rootB = find(parent, b);
        if (!rootA.equals(rootB)) {
            parent.put(rootA, rootB);
        }
    }
}
