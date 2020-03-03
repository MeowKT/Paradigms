package queue;

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