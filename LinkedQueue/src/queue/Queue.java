package queue;

import java.util.function.Predicate;

public interface Queue {
    /**
     * Inv :
     * n >= 0
     * and for all i : a[i] != null
     */

    /**
     * Pre :
     * x != null
     *
     * Post :
     * n' = n + 1
     * and a[i]' = a[i] for i in 0..n - 1
     * and a[n]' = x
     */
    void enqueue(Object x);

    /**
     * Pre:
     * n > 0
     *
     * Post :
     * n' = n
     * and a[i]' = a[i] for i in 0..n - 1
     * and Result = a[0]'
     */
    Object element();

    /**
     * Pre:
     * predicate != null
     *
     * Post :
     * n' <= n
     * Exists sequence i_0, i_1..i_n'-1:
     * 0 <= i_0 < i_1 < .. < i_n'-1 <= n - 1
     *
     * and for (j in 0..n'-1):
     * a[j]' = a[i_j]
     * and predicate(a[i_j]) == true
     *
     * and for (k in 0..n - 1)
     * if (k != i_0 and k != i_1 and ... and k != i_(n'-1)) -> predicate(a[k]) == false
     */
    void retainIf(Predicate<Object> predicate);

    /**
     * Pre:
     * predicate != null
     *
     * Post :
     * n' <= n
     * Exists sequence i_0, i_1..i_n'-1:
     * 0 <= i_0 < i_1 < .. < i_n'-1 <= n - 1
     *
     * and for (j in 0..n'-1):
     * a[j]' = a[i_j]
     * and predicate(a[i_j]) == false
     *
     * and for (k in 0..n - 1)
     * if (k != i_0 and k != i_1 and ... and k != i_(n'-1)) -> predicate(a[k]) == true
     */
    void removeIf(Predicate<Object> predicate);

    /**
     * Pre:
     * predicate != null
     *
     * Post :
     * n' <= n
     * exists k:
     * n' = n - k
     * for (i in k..n - 1)
     *    a[i - k]' = a[i]
     * and for (i in 0..k - 1)
     *    predicate(a[i]) = false
     * and (k == n) or (predicate(a[k]) = true)
     */
    void dropWhile(Predicate<Object> predicate);

    /**
     * Pre:
     * predicate != null
     *
     * Post :
     * n' <= n
     * for (i in 0..(n' -1)):
     * a[i]' = a[i] and predicate(a[i]) == true
     * and (n' == n) or (n' < n -> predicate(a[n']) == false)
     */
    void takeWhile(Predicate<Object> predicate);

    /**
     * Pre:
     * n > 0
     *
     * Post:
     * n' == n - 1
     * and (a'[i - 1] == a[i] for i in 1..n - 1)
     * and (Result == a[0])
     */
    Object dequeue();

    /**
     * Pre:
     * true
     *
     * Post:
     * Result = n
     * and n' = n
     * and a[i'] = a[i] for i in 0..n-1
     */
    int size();

    /**
     * Pre:
     * true
     *
     * Post:
     * Result = (size == 0)
     * n' = n
     * and a[i'] = a[i] for i in 0..n-1
     */
    boolean isEmpty();

    /**
     * Pre:
     * true
     *
     * Post:
     * n' = 0
     */
    void clear();


}