package queue;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

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

    public void removeIf(Predicate<Object> predicate) {
        doIf(predicate.negate());
    }

    public void retainIf(Predicate<Object> predicate) {
        doIf(predicate);
    }

    public void doIf(Predicate<Object> predicate) {
        int oldSize = size;
        for (int i = 0; i < oldSize; i++) {
            Object x = dequeue();
            if (predicate.test(x)) {
                enqueue(x);
            }
        }
    }

    public void takeWhile(Predicate<Object> predicate) {
        doWhile(predicate, () -> enqueue(dequeue()), t -> t);
    }

    public void dropWhile(Predicate<Object> predicate) {
        doWhile(predicate, () -> dequeue(), t -> 0);
    }

    @FunctionalInterface
    interface QueueOperation {
        void operate();
    }

    private void doWhile(Predicate<Object> predicate, QueueOperation operation, UnaryOperator<Integer> unaryOperator) {
        int sz = size;
        for (int i = 0; i < sz; i++) {
            Object x = element();
            if (predicate.test(x)) {
                operation.operate();
            } else {
                deleteFirstElements(unaryOperator.apply(sz - i));
                return;
            }
        }
    }

    private void deleteFirstElements(int cnt) {
        for (int i = 0; i < cnt; i++) {
            dequeue();
        }
    }
}
