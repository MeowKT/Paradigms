package queue;

// n - count elements
// a - sequence

public class ArrayQueue {
    /**
     * Inv :
     * n >= 0
     * and for all i : a[i] != null
     */
    private Object[] elements;
    private int tail, head;
    public ArrayQueue() {
        tail = head = 0;
        elements = new Object[1];
    }

    /**
     * Pre :
     * x != null
     *
     * Post :
     * n' = n + 1
     * and a[i]' = a[i] for i in 0..n - 1
     * and a[n]' = x
     */
    public void enqueue(Object x) {
        assert x != null;

        ensureCapacity(size() + 1);
        elements[tail] = x;
        tail = next(tail);
    }

    /**
     * Pre:
     * x != null
     *
     * Post :
     * n' = n + 1
     * and a[i + 1]' = a[i] for i in 0..n - 1
     * and a[0]' = x
     */
    public void push(Object x) {
        assert x != null;

        ensureCapacity(size() + 1);
        head = prev(head);
        elements[head] = x;
    }

    /**
     * Pre:
     * n > 0
     *
     * Post :
     * n' = n
     * and a[i]' = a[i] for i in 0..n - 1
     * and Result = a[0]'
     */
    public Object element() {
        return elements[head];
    }

    /**
     * Pre:
     * n > 0
     *
     * Post :
     * n' = n
     * and a[i]' = a[i] for i in 0..n - 1
     * and Result = a[n - 1]'
     */
    public Object peek() {
        return elements[prev(tail)];
    }

    /**
     * Pre:
     * n > 0
     *
     * Post:
     * n' == n - 1
     * and (a'[i - 1] == a[i] for i in 1..n - 1)
     * and (Result == a[0])
     */
    public Object dequeue() {
        Object x = element();
        elements[head] = null;
        head = next(head);
        return x;
    }

    /**
     * Pre:
     * size > 0
     *
     * Post:
     * n' == n - 1
     * and (a'[i] == a[i] for i = 0...n - 2)
     * and (Result == a[n - 1])
     */
    public Object remove() {
        Object x = peek();
        tail = prev(tail);
        elements[tail] = null;
        return x;
    }

    /**
     * Pre:
     * ind in 0..n - 1
     *
     * Post:
     * Result = a[ind]
     * and a[i]' = a[i] for i in 0..n-1
     */
    public Object get(int ind) {
        return elements[(head + ind) % elements.length];
    }

    /**
     * Pre:
     * ind in 0..size
     * el != null
     *
     * Post:
     * a[i]' = a[i] for i in 0..ind-1
     * and a[i]' = a[i] for i in ind+1..n-1
     * a[ind]' = el
     */
    public void set(int ind, Object el) {
        elements[(head + ind) % elements.length] = el;
    }

    /**
     * Pre:
     * always true
     * Post:
     * Result = n
     * and n' = n
     * and a[i'] = a[i] for i in 0..n-1
     */
    public int size() {
        if (head > tail) {
            return elements.length - head + tail;
        } else {
            return tail - head;
        }
    }


    /**
     * Pre:
     * sz >= 0
     *
     * Post:
     * (n' == n)
     * and (a[i]' == a[i] for i = 0...n - 1)
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

    /**
     * Pre:
     * always true
     *
     * Post:
     * Result = (size == 0)
     * n' = n
     * and a[i'] = a[i] for i in 0..n-1
     */
    public boolean isEmpty() {
        return tail == head;
    }

    /**
     * Pre:
     * always true
     *
     * Post:
     * n' = 0
     */
    public void clear() {
        tail = head = 0;
        elements = new Object[1];
    }

    /**
     * Pre:
     * (elements.length != 0)
     * and (0 <= x < elements.length)
     *
     * Post:
     * Result = (x + 1) % elements.length
     */
    private int next(int x) {
        return (x + 1) % elements.length;
    }

    /**
     * Pre: (elements.length != 0)
     * and (0 <= x < elements.length)
     *
     * Post:
     * Result = (x - 1 + elements.length) % elements.length
     */
    private int prev(int x) {
        return (x - 1 + elements.length) % elements.length;
    }
}
