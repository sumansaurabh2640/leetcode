class Solution {

    static class Node {
        int[] count;
        int product;

        Node(int k) {
            count = new int[k];
        }
    }

    private int n;
    private int k;
    private Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int[] q : queries) {
            int index = q[0];
            int value = q[1];
            int start = q[2];
            int x = q[3];

            update(1, 0, n - 1, index, value % k);

            Node res = query(1, 0, n - 1, start, n - 1);

            answer[queriesIndex++] = res.count[x];
        }

        return answer;
    }

    private int queriesIndex = 0;

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            tree[node] = new Node(k);

            int value = nums[l] % k;
            tree[node].product = value;
            tree[node].count[value] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            tree[node] = new Node(k);
            tree[node].product = value;
            tree[node].count[value] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    private Node merge(Node a, Node b) {
        Node res = new Node(k);

        for (int r = 0; r < k; r++) {
            res.count[r] += a.count[r];
        }

        for (int r = 0; r < k; r++) {
            int remainder = (a.product * r) % k;
            res.count[remainder] += b.count[r];
        }

        res.product = (a.product * b.product) % k;

        return res;
    }
}