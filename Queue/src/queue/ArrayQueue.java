package queue;

// n - count elements
// a - sequence

public class ArrayQueue {
    /** Inv :
     * n >= 0
     * and for all i : a[i] != null
     */
    private Object[] elements;
    private int tail, head;
    public ArrayQueue() {
        tail = head = 0;
        elements = new Object[1];
    }

    /** Pre :
     * x != null
     */
    public void enqueue(Object x) {
        assert x != null;

        ensureCapacity(size() + 1);
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
    public void push(Object x) {
        assert x != null;

        ensureCapacity(size() + 1);
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
    public Object element() {
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
    public Object peek() {
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
    public Object dequeue() {
        Object x = element();
        elements[head] = null;
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
    public Object remove() {
        Object x = peek();
        tail = prev(tail);
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
    public Object get(int ind) {
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
    public void set(int ind, Object el) {
        elements[(head + ind) % elements.length] = el;
    }
    /** Post:
     * a[i]' = a[i] for i in 0..ind-1
     * and a[i]' = a[i] for i in ind+1..n-1
     * a[ind]' = el
     */

    public int size() {
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
    private void ensureCapacity(int size) {
        if (size == elements.length) {
            Object[] arr = new Object[2 * elements.length];
            int sz = 0;
            while (tail != head) {
                arr[sz++] = elements[head];
                head = next(head);
            }
            elements = arr;
            head = 0;
            tail = sz;
        }
    }
    /** Post:
     * (n' == n) && (a[i]' == a[i] for i = 0...n - 1)
     */

    public boolean isEmpty() {
        return tail == head;
    }
    /** Post:
     * Result = (size == 0)
     * n' = n
     * and a[i'] = a[i] for i in 0..n-1
     */

    public void clear() {
        tail = head = 0;
        elements = new Object[1];
    }
    /** Post:
     *  n' = 0
     */

    /** Pre: (elements.length != 0)
     * and (0 <= x < elements.length)
     */
    private int next(int x) {
        return (x + 1) % elements.length;
    }
    /** Post:
     * Result = (x + 1) % elements.length
     */

    /** Pre: (elements.length != 0)
     * and (0 <= x < elements.length)
     */
    private int prev(int x) {
        return (x - 1 + elements.length) % elements.length;
    }
    /** Post:
     * Result = (x - 1 + elements.length) % elements.length
     */
}
