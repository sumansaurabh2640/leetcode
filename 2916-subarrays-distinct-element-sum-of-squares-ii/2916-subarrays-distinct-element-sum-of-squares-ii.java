import java.util.*;

class Solution {
    static final long MOD = 1000000007L;

    long[] sum;
    long[] sq;
    long[] lazy;
    int n;

    void apply(int node, int l, int r, long v) {
        long len = r - l + 1L;

        sq[node] = (sq[node]
                + 2 * v % MOD * sum[node] % MOD
                + len % MOD * v % MOD * v % MOD) % MOD;

        sum[node] = (sum[node] + len % MOD * v) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;

        int mid = (l + r) / 2;

        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);

        lazy[node] = 0;
    }

    void update(int node, int l, int r, int ql, int qr, long v) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            apply(node, l, r, v);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        update(node * 2, l, mid, ql, qr, v);
        update(node * 2 + 1, mid + 1, r, ql, qr, v);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long answer = 0;

        for (int r = 0; r < n; r++) {
            int x = nums[r];
            int prev = last[x];

            update(1, 0, n - 1, prev + 1, r, 1);

            answer = (answer + sq[1]) % MOD;

            last[x] = r;
        }

        return (int) answer;
    }
}