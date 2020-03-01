package queue;

// n - count elements
// a - sequence

public class ArrayQueueModule {
    /** Inv :
     * n >= 0
     * and for all i : a[i] != null
     */
    private static Object elements[] = new Object[1];
    private static int tail = 0, head = 0;

    /** Pre :
     * x != null
     */
    public static void enqueue(Object x) {
        assert x != null;

        resize(size() + 1);
        elements[tail] = x;
        tail = next(tail);
    }
    /** Post :
     * n' = n + 1
     * and a[i]' = a[i] for i in 0..n - 1
     * and a[n]' = x
     */

    /** Pre:
     * x != null
     */
    public static void push(Object x) {
        assert x != null;

        resize(size() + 1);
        head = prev(head);
        elements[head] = x;
    }
    /** Post :
     * n' = n + 1
     * and a[i + 1]' = a[i] for i in 0..n - 1
     * and a[0]' = x
     */

    /** Pre:
     * n > 0
     */
    public static Object element() {
        assert tail != head;

        return elements[head];
    }
    /** Post :
     * n' = n
     * and a[i]' = a[i] for i in 0..n - 1
     * and Result = a[0]'
     */

    /** Pre:
     * n > 0
     */
    public static Object peek() {
        return elements[prev(tail)];
    }
    /** Post :
     * n' = n
     * and a[i]' = a[i] for i in 0..n - 1
     * and Result = a[n - 1]'
     */

    /** Pre:
     * n > 0
     */
    public static Object dequeue() {
        Object x = elements[head];
        head = next(head);
        return x;
    }
    /** Post:
     * n' == n - 1
     * and (a'[i - 1] == a[i] for i in 1..n - 1)
     * and (Result == a[0])
     */

    /** Pre:
     * size > 0
     */
    public static Object remove() {
        tail = prev(tail);
        Object x = elements[tail];
        elements[tail] = null;
        return x;
    }
    /** Post:
     * n' == n - 1
     * and (a'[i] == a[i] for i = 0...n - 2)
     * and (Result == a[n - 1])
     */

    /** Pre:
     * ind in 0..n - 1
     */
    public static Object get(int ind) {
        return elements[(head + ind) % elements.length];
    }
    /** Post:
     * Result = a[ind]
     * and a[i]' = a[i] for i in 0..n-1
     */

    /** Pre:
     * ind in 0..size
     * el != null
     */
    public static void set(int ind, Object element) {
        elements[(head + ind) % elements.length] = element;
    }
    /** Post:
     * a[i]' = a[i] for i in 0..ind-1
     * and a[i]' = a[i] for i in ind+1..n-1
     * a[ind]' = el
     */

    public static int size() {
        if (head > tail) {
            return elements.length - head + tail;
        } else {
            return tail - head;
        }
    }
    /** Post:
     * Result = n
     * and n' = n
     * and a[i'] = a[i] for i in 0..n-1
     */

    /**Pre:
     * sz >= 0
     */
    private static void resize(int size) {
        if (size == elements.length) {
            Object[] arr = new Object[2 * elements.length];
            int sz = 0;
            while (tail != head) {
                arr[sz++] = elements[head];
                head = next(head);
            }
            arr[sz++] = elements[head];
            elements = arr;
            head = 0;
            tail = sz - 1;
        }
    }
    /** Post:
     * (n' == n) && (a[i]' == a[i] for i = 0...n - 1)
     */

    public static boolean isEmpty() {
        return tail == head;
    }
    /** Post:
     * Result = (size == 0)
     * n' = n
     * and a[i'] = a[i] for i in 0..n-1
     */

    public static void clear() {
        tail = head = 0;
        elements = new Object[1];
    }
    /** Post:
     *  n' = 0
     */

    /** Pre: (elements.length != 0)
     * and (0 <= x < elements.length)
     */
    private static int next(int x) {
        return (x + 1) % elements.length;
    }
    /** Post:
     * Result = (x + 1) % elements.length
     */

    /** Pre: (elements.length != 0)
     * and (0 <= x < elements.length)
     */
    private static int prev(int x) {
        return (x - 1 + elements.length) % elements.length;
    }
    /** Post:
     * Result = (x - 1 + elements.length) % elements.length
     */
}
