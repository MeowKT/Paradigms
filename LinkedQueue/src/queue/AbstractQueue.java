package queue;

import java.util.function.Consumer;
import java.util.function.Function;
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

    public void takeWhile(Predicate<Object> predicate) {
        doWhile(predicate, queue -> enqueue(dequeue()), t -> t);
    }

    public void dropWhile(Predicate<Object> predicate) {
        doWhile(predicate, queue -> dequeue(), t -> 0);
    }

    public void doIf(Predicate<Object> predicate) {
        int sz = size;
        for (int i = 0; i < sz; i++) {
            Object x = dequeue();
            if (predicate.test(x)) {
                enqueue(x);
            }
        }
    }

    public void doWhile(Predicate<Object> predicate, Consumer<Queue> consumer, UnaryOperator<Integer> unaryOperator) {
        int sz = size;
        for (int i = 0; i < sz; i++) {
            Object x = element();
            if (predicate.test(x)) {
                consumer.accept(this);
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
