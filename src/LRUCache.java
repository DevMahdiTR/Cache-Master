import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache<K, V> implements Cache<K, V> {
    private final int maxSize;
    private final Map<K, V> cache;
    private final Lock lock;

    public LRUCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be greater than 0");
        }
        this.maxSize = maxSize;
        this.cache = new LinkedHashMap<>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
        this.lock = new ReentrantLock();
    }

    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value must not be null");
        }

        try {
            lock.lock();
            cache.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        try {
            lock.lock();
            return cache.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        try {
            lock.lock();
            cache.remove(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lock();
            cache.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        try {
            lock.lock();
            return cache.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        try {
            lock.lock();
            return cache.containsKey(key);
        } finally {
            lock.unlock();
        }
    }
}