package search;

public class BinarySearchShift {

    // Pre : {a_{k + 1}.. a_{n}, a_{1}.. a_{k}} for all i, j : i < j <-> a[i] < a[j]
    static int binIter(int a[]) {
        // Pre
        int l = -1, r = a.length;
        /* Inv:
         * (r - l >= 1)
         * and (r' - l' < r - l)
         * and (r == a.length or r < a.length and a[r] <= a.back())
         * and (l == -1 or 0 <= l < a.length and a[l] > a.back())
         */
        while (r - l > 1) {
            // Pre and Inv and r > l + 1
            int m = (r + l - (r - l) % 2) / 2;
            /* Pre and Inv
             * and r > l + 1
             * and m = (r + l - (r - l) % 2) / 2
             * and l < m < r
             */
            if (a[m] > a[a.length - 1]) {
                /* Pre and Inv
                 * and r > l + 1
                 * and m = (r + l - (r - l) % 2) / 2
                 * and l < m < r
                 * and a[m] > a.back()
                 */
                l = m;
                /* r' = r and ((r' = a.length) or (0 < r' < a.length and a[r'] <= a.back()))
                 * (Pre and a[m] > a.back() and l < m < r and l' = m) --> (0 <= l' < a.length and a[l'] > a.back()
                 * (r > l + 1 and l' = m and l < m < r) --> r' - l' >= 1 and r' - l' < r - l
                 */

                // Pre and Inv
            } else {
                /* Pre and Inv
                 * and r > l + 1
                 * and m = (r + l - (r - l) % 2) / 2
                 * and l < m < r
                 * and a[m] < a.back()
                 */
                r = m;
                /* (l' = l) --> (l' == -1 or 0 <= l' < a.length and a[l'] > a.back())
                 * (Pre and a[m] < a.back() and l < m < r and r' = m) --> (0 <= r' < a.length and a[r'] <= a.back())
                 * (r > l + 1 and r' = m and l < m < r) --> r' - l' >= 1 and r' - l' < r - l
                 */

                // Pre and Inv
            }

        }
        /* !((r - l) > 1) and (r - l >= 1) --> r - l == 1
         * Pre and Inv and l + 1 == r :
         * 1. a.length == 0 or a.sorted --> r = 0, l = -1
         * 2. 0 <= l < a.size and Inv --> a[l] > a.back()
         * 2.1) l = a.size - 1
         * 2.2) l < a.size - 1 and Inv and Pre and r - l == 1 --> a[l + 1] > a[l]
         */
        return r;
    }
    /** Post: (a[i]' == a[i])
     * and (a.size == 0 or a.sorted <-> res == -1)
     * or (res < a.size and a[res] > a.back and (res == a.size - 1 or a[res + 1] < a[res]))
     */

    /** Pre:
     * and ({e_{k + 1}.. e_{n}, e_1.. e_k} for all i, j : i < j <-> a[i] < a[j])
     * and (r == a.size or (r < a.size and a[r] <= a.back))
     * and (l == -1 or (0 <= l < a.size and a[l] > a.back))
     * and l < r
     * and r' - l' < r - l
     */
    /** Post: (a[i]' == a[i])
     * and res == -1 or (a[res] > a.back() and (res == a.size() - 1 or a[res + 1] < a.back))
     */
    static int binRec(int[] a, int l, int r) {
        // Pre
        if (r - l == 1) {
            /* Pre and l + 1 == r
             * 1) a.size == 0 -> r == 0 and l == -1
             * 2) l != -1  ->  a[l] > a.back()
             * 2a) l == n - 1
             * 2b) l < n - 1 and Inv and (l + 1 == r) -> a[l + 1] < a[l]
             */
            return r;
        } else {
            // Pre and r > l + 1
            int m = (l + r - (r - l) % 2) / 2;
            // Pre and r > l + 1 and m == (l + r - (r - l) % 2) / 2 and l < m < r
            if (a[m] > a[a.length - 1]) {
                /* Pre and r > l + 1 and m = (r + l - (r - l) % 2) / 2 and a[m] > a.back()
                 * a[i]' == a[i]
                 * r' == r  -> ((r == a.size) or (r' < a.size and a[r'] <= a.back()))
                 * Pre and a[m] > a.back() and l < m < r and l' == m ->  (0 <= l' < a.size and a[l'] > a.back())
                 * r > l + 1 and l' = m and l < m < r  ->  r' - l' >= 1 and r' - l' < r - l
                 * Pre for l' and r'
                 */
                return binRec(a, m, r);
                // ok because Post
            } else {
                /* Pre and r > l + 1 and m == (l + r - (r - l) % 2) / 2 and l < m < r and a[m] < a.back()
                 * l' == l  ->  (l == -1 or (0 <= l < a.size and a[l] > a.back()))
                 * Pre and a[m] < a.back() and l < m < r and r = m  -> (r < a.size and a[r] <= a.back())
                 * r > l + 1 and r' == m and l < m < r  -> r' - l' >= 1 and r' - l' < r - l
                 * Pre for l' and r'
                 */
                return binRec(a, l, m);
                // Ok because Post
            }
        }
    }


    /** Pre
     * any args[i] -> Integer
     * and {args_{k + 1}.. args_{n}, args_{1}.. args_{k}} for all i, j : i < j <-> a[i] < a[j]
     */
    public static void main(String[] args) {
        int n = args.length;

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(args[i]);
        }

        int ans = binIter(a);
        //int ans = binRec(a, -1, n);

        System.out.println(ans);
    }
    /** Post : ans in 0..a.size() - 1
     * and (a.size <= 1 and ans == 0)
     * or (a.size >= 2 and (ans == 0 or a[ans] <= a.back() and a[ans - 1] > a[ans]))
     */
}
