// class Solution {
//     public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        
//     }
// }
class Solution {

    int[] leftChar, rightChar;
    int[] prefix, suffix, best, len;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int size = 4 * n;

        leftChar = new int[size];
        rightChar = new int[size];
        prefix = new int[size];
        suffix = new int[size];
        best = new int[size];
        len = new int[size];

        build(s, 1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            ans[i] = best[1];
        }

        return ans;
    }

    private void build(String s, int node, int l, int r) {
        len[node] = r - l + 1;

        if (l == r) {
            int c = s.charAt(l);

            leftChar[node] = c;
            rightChar[node] = c;
            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(s, node * 2, l, mid);
        build(s, node * 2 + 1, mid + 1, r);

        merge(node);
    }

    private void update(int node, int l, int r, int index, char c) {
        if (l == r) {
            leftChar[node] = c;
            rightChar[node] = c;
            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, c);
        } else {
            update(node * 2 + 1, mid + 1, r, index, c);
        }

        merge(node);
    }

    private void merge(int node) {
        int L = node * 2;
        int R = node * 2 + 1;

        leftChar[node] = leftChar[L];
        rightChar[node] = rightChar[R];

        prefix[node] = prefix[L];
        suffix[node] = suffix[R];

        best[node] = Math.max(best[L], best[R]);

        if (rightChar[L] == leftChar[R]) {

            best[node] = Math.max(
                best[node],
                suffix[L] + prefix[R]
            );

            if (prefix[L] == len[L]) {
                prefix[node] = len[L] + prefix[R];
            }

            if (suffix[R] == len[R]) {
                suffix[node] = len[R] + suffix[L];
            }
        }
    }
}