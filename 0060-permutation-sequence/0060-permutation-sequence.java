class Solution {
    public String getPermutation(int n, int k) {

        // Store numbers 1 to n
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        // factorial[i] = i!
        int[] factorial = new int[n + 1];
        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // Convert k to 0-based index
        k--;

        StringBuilder result = new StringBuilder();

        for (int i = n; i >= 1; i--) {

            // Each group contains (i-1)! permutations
            int blockSize = factorial[i - 1];

            // Find which block k belongs to
            int index = k / blockSize;

            // Select that number
            result.append(numbers.get(index));

            // Remove it from available numbers
            numbers.remove(index);

            // Find position inside the selected block
            k = k % blockSize;
        }

        return result.toString();
    }
}

