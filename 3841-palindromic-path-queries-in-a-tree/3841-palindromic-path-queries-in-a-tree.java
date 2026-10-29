import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[][] up;
    private int[] depth, tin, tout;
    private int[] bit;
    private int timer;
    private int LOG;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        timer = 0;

        // Iterative DFS avoids stack overflow on a deep tree.
        int[] parent = new int[n];
        int[] next = new int[n];
        int[] stack = new int[n];
        int top = 0;
        stack[0] = 0;
        parent[0] = 0;

        while (top >= 0) {
            int u = stack[top];

            if (next[u] == 0) {
                tin[u] = timer++;
            }

            if (next[u] < adj[u].size()) {
                int v = adj[u].get(next[u]++);

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[++top] = v;
            } else {
                tout[u] = timer;
                top--;
            }
        }

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];
        for (int u = 0; u < n; u++) {
            up[0][u] = parent[u];
        }

        for (int j = 1; j < LOG; j++) {
            for (int u = 0; u < n; u++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }
        }

        // XOR Fenwick tree: each node's character affects its whole subtree.
        bit = new int[n + 2];

        for (int u = 0; u < n; u++) {
            int mask = 1 << (s.charAt(u) - 'a');
            add(tin[u] + 1, mask);

            if (tout[u] < n) {
                add(tout[u] + 1, mask);
            }
        }

        char[] chars = s.toCharArray();
        List<Boolean> ans = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.trim().split("\\s+");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int delta = oldMask ^ newMask;

                if (delta != 0) {
                    add(tin[u] + 1, delta);

                    if (tout[u] < n) {
                        add(tout[u] + 1, delta);
                    }

                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int maskU = prefix(tin[u] + 1);
                int maskV = prefix(tin[v] + 1);
                int ancestorMask = 1 << (chars[ancestor] - 'a');

                int pathMask = maskU ^ maskV ^ ancestorMask;

                // At most one character may have an odd frequency.
                ans.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return ans;
    }

    private void add(int index, int value) {
        while (index < bit.length) {
            bit[index] ^= value;
            index += index & -index;
        }
    }

    private int prefix(int index) {
        int result = 0;

        while (index > 0) {
            result ^= bit[index];
            index -= index & -index;
        }

        return result;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if (((diff >> j) & 1) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
}