package queue;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public void enqueue(Object elem) {
        assert elem != null;

        enqueueImpl(elem);
        size++;
    }

    protected abstract void enqueueImpl(Object elem);

    public Object element() {
        assert size > 0;

        return elementImpl();
    }

    protected abstract Object elementImpl();

    public Object dequeue() {
        Object result = element();
        dequeueImpl();
        size--;
        return result;
    }

    protected abstract void dequeueImpl();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        clearImpl();
        size = 0;
    }

    protected abstract void clearImpl();
}
