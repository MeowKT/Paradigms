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
     * and a[i] in a' if predicate(a[i]) == true
     * and for all i != j in 0..n - 1:
     * if (i < j and a[i] in a' and a[j] in a') --> index(a[i]) in a' < index(a[j]) in a'
     */
    void retainIf(Predicate<Object> predicate);

    /**
     * Pre:
     * predicate != null
     *
     * Post :
     * n' <= n
     * and a[i] in a' if predicate(a[i]) == false
     * and for all i != j in 0..n - 1:
     * if (i < j and a[i] in a' and a[j] in a') --> index(a[i]) in a' < index(a[j]) in a'
     */
    void removeIf(Predicate<Object> predicate);

    /**
     * Pre:
     * predicate != null
     *
     * Post :
     * n' <= n
     * and n' == 0 or (exist k:
     * a[i] in a' if i in k..n-1
     * and predicate(a[k]) == false
     * and predicate(a[j]) == true for j in 0..k-1
     * and for all i != j in 0..n - 1:
     * if (i < j and a[i] in a' and a[j] in a') --> index(a[i]) in a' < index(a[j]) in a')
     */
    void dropWhile(Predicate<Object> predicate);

    /**
     * Pre:
     * predicate != null
     *
     * Post :
     * n' <= n
     * and n' = 0 or (exist k :
     * a[i] in a' for i in 0..k-1
     * and predicate(a[i]) == true
     * and predicate(a[k]) == false
     * and for all i != j in 0..n - 1:
     * if (i < j and a[i] in a' and a[j] in a') --> index(a[i]) in a' < index(a[j]) in a')
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