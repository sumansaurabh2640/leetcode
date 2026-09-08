// class Solution {
//     public int countPrimes(int n) {
//         if (n <= 2) {
//             return 0;
//         }

//         boolean[] isPrime = new boolean[n];

//         // Assume all numbers are prime
//         for (int i = 2; i < n; i++) {
//             isPrime[i] = true;
//         }

//         // Sieve of Eratosthenes
//         for (int i = 2; i * i < n; i++) {

//             if (isPrime[i]) {

//                 // Start from i * i
//                 for (int j = i * i; j < n; j += i) {
//                     isPrime[j] = false;
//                 }
//             }
//         }

//         int count = 0;

//         for (int i = 2; i < n; i++) {
//             if (isPrime[i]) {
//                 count++;
//             }
//         }

//         return count;
//     }
// }

class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] isPrime = new boolean[n];

        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }
}