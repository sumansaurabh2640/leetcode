
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] original = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            original[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            original[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        newParent[0] = -1;

        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] previous = new int[n];
        int[] stack = new int[n];
        int[] nextChild = new int[n];

        int top = 0;
        stack[0] = 0;

        // Iterative DFS on the original tree.
        while (top >= 0) {
            int u = stack[top];
            int c = s.charAt(u) - 'a';

            if (nextChild[u] == 0) {
                previous[u] = last[c];

                if (u != 0) {
                    newParent[u] = (last[c] != -1)
                        ? last[c]
                        : parent[u];
                }

                last[c] = u;
            }

            if (nextChild[u] < original[u].size()) {
                int v = original[u].get(nextChild[u]++);
                stack[++top] = v;
            } else {
                last[c] = previous[u];
                top--;
            }
        }

        // Build the modified tree.
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }

        // Get traversal order of the modified tree.
        int[] order = new int[n];
        int size = 0;

        stack = new int[n];
        top = 0;
        stack[0] = 0;

        while (top >= 0) {
            int u = stack[top--];
            order[size++] = u;

            for (int v : tree[u]) {
                stack[++top] = v;
            }
        }

        // Calculate subtree sizes in reverse order.
        int[] answer = new int[n];
        Arrays.fill(answer, 1);

        for (int i = size - 1; i > 0; i--) {
            int u = order[i];
            answer[newParent[u]] += answer[u];
        }

        return answer;
    }
}
