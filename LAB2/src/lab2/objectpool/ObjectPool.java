package lab2.objectpool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.function.Supplier;

public class ObjectPool<T> {
    private final Deque<T> pool = new ArrayDeque<>();
    private final Supplier<T> creator;
    private final int maxSize;

    public ObjectPool(int maxSize, Supplier<T> creator) {
        if (maxSize <= 0) throw new IllegalArgumentException("maxSize must be > 0");
        this.maxSize = maxSize;
        this.creator = Objects.requireNonNull(creator);
    }

    public synchronized T acquire() {
        T obj = pool.pollFirst();
        if (obj == null) {
            obj = creator.get();
        }
        return obj;
    }

    public synchronized void release(T obj) {
        if (obj == null) return;
        if (pool.size() < maxSize) {
            pool.addFirst(obj);
        }
        // else drop on the floor
    }

    public synchronized int available() {
        return pool.size();
    }
}
