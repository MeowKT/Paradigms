package search;

public class BinarySearchSpan {
    // Pre : i < j -> a[i] >= a[j], k' = k
    static int binIter(int a[], int k) {
        // Pre
        int l = -1, r = a.length;
        /* Inv:
         * (r - l >= 1) and (r' - l' <= (r - l + (r - l) % 2) / 2)
         * and (r == a.length or r < a.length && a[r] < k)
         * and (l == -1 or 0 <= l < a.length && a[l] >= k)
         */
        while (r - l > 1) {
            // Pre and Inv and r > l + 1
            int m = (r + l - (r - l) % 2) / 2;
            /* Pre and Inv
             * and r > l + 1
             * and m = (r + l + (r - l) % 2) / 2
             * and l < m < r
             */
            if (a[m] >= k) {
                /* Pre and Inv
                 * and r > l + 1
                 * and m = (r + l + (r - l) % 2) / 2
                 * and l < m < r
                 * and a[m] >= key
                 */
                l = m;
                /* (l' == m and r' = r) --> r' - m = r - (l + r - (r - l) % 2) / 2 = (r - l + (r - l) % 2) / 2
                 * r' = r and ((r' = a.length) or (0 < r' < a.length and a[r'] < k))
                 * (Pre and a[m] > k and l < m < r and l' = m) --> (0 <= l' < a.length and a[l'] >= k
                 * (r > l + 1 and l' = m and l < m < r) --> r' - l' >= 1
                 */

                // Pre and Inv
            } else {
                /* Pre and Inv
                 * and r > l + 1
                 * and m = (r + l + (r - l) % 2) / 2
                 * and l < m < r
                 * and a[m] < key
                 */
                r = m;
                /* (r' == m and l' = l) --> m - l' = (r + l + (r - l) % 2) / 2 - l' == (r - l + (r - l) % 2) / 2
                 * (l' = l) --> (l' == -1 or 0 <= l' < a.length and a[l'] >= key)
                 * (Pre and a[m] <= k and l < m < r and r' = m) --> (0 <= r' < a.length and a[r'] < k)
                 * (r > l + 1 and r' = m and l < m < r) --> r' - l' >= 1
                 */

                // Pre and Inv
            }

        }
        /* !((r - l) > 1) and (r - l >= 1) --> r - l == 1
         * Pre and Inv and l + 1 == r :
         * 1. a.length == 0 --> r = 0, l = -1
         * 2. a.back() > x --> r = a.length and l = a.length - 1
         * 3. 0 <= l < a.size and Inv --> a[l] >= x
         * 3.1) l = a.size - 1
         * 3.2) l < a.size - 1 and Inv and Pre and r - l == 1 --> a[l + 1] < a[l]
         */
        return l;
    }
    /** Post: (a[i]' == a[i])
     * and (a.size == 0 <-> res == 0)
     * or (a.last > x <-> res == a.size)
     * or (res < a.size and a[res] <= key and (res == 0 or a[res + 1] < a[res]))
     */

    /** Pre: i < j -> a[i] >= a[j]
     * and (r == a.size or (r < a.size and a[r] <= key))
     * and (l == -1 || (0 <= l < a.size and a[l] > key))
     * and (r' - l' <= (r - l + (r - l) % 2) div 2)
     * and (r - l >= 1)
     * and k' = k
     */
    static int binRec(int a[], int k, int l, int r) {
        if (r - l == 1) {
            /* Pre && l + 1 == r
             * 1) a.size == 0 --> r' == 0 && l' == -1
             * 2) a.back() > x --> r' == a.size() && l' = a.size() - 1
             * 3) r' < a.size -> a[r'] <= key
             * 3a) r' == 0
             * 3b) (r' > 0 and (l' + 1 == r')) --> a[r' - 1] > key
             */
            return r;
        } else {
            // Pre and r > l + 1
            int m = (r + l - (r - l) % 2) / 2;
            // Pre && r > l + 1 && m == (l + r + (r - l) % 2) / 2 && l < m < r
            if (a[m] > k) {
                // Pre and r > l + 1 and m = (r + l + (r - l) % 2) / 2 and a[m] >= k
                // (l' == m && r' = r) --> r' - m == r - (r + l + (r - l) % 2) / 2 == (r - l + (r - l) % 2) / 2
                // r' == r  -> ((r == a.size) || (r' < a.size && a[r'] <= k))
                // Pre && a[l'] >= k && l < m < r  -->  (0 <= l' < a.size && a[l'] > k)
                // r > l + 1 && l' = m && l < m < r  -->  r' - l' >= 1
                return binRec(a, k, m, r);
            } else {
                // Pre and r > l + 1 and m == (l + r + (r - l) % 2) / 2 and l < m < r and a[m] < key
                // l' == l  -->  (l == -1 || (0 <= l < a.size && a[l] > key))
                // Pre && a[m] < key && l < m < r - ->  (r < a.size && a[r] <= key)
                // r' == m && l' = l  -->  m - l' == (l + r + (r - l) % 2) / 2 - l == (r - l + (r - l) % 2) / 2
                // r > l + 1 && r' == m && l < m < r --> r' - l' >= 1
                return binRec(a, k, l, m);
            }
        }
    }
    /** Post: (a[i]' == a[i])
     * and (a.size == 0 and res == 0) or (a.back() > x && res == a.size) or (res < a.size && a[res] <= key && (res == 0 or a[res - 1] > key))
     */

    public static void main(String[] args) {

        int n = args.length - 1;
        int key = Integer.parseInt(args[0]);
        int[] a = new int[n];

        for (int i = 1; i <= n; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
        }

        int l = binRec(a, key, -1, n);
        int r = binIter(a, key);

        System.out.println(l + " " + (r - l + 1));
    }
}
