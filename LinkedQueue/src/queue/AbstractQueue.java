package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue, Cloneable {
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

    @Override
    protected abstract Queue clone();

    public Queue map(Function<Object, Object> function) {
        Queue queue = clone();
        for (int i = 0; i < queue.size(); i++) {
            queue.enqueue(function.apply(queue.dequeue()));
        }
        return queue;
    }

    public Queue filter(Predicate<Object> predicate) {
        Queue queue = clone();
        for (int i = 0; i < size(); i++) {
            Object x = queue.dequeue();
            if (predicate.test(x)) {
                queue.enqueue(x);
            }
        }
        return queue;
    }
}
