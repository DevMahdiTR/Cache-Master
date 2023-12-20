package cache;

import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LIFOCache is a thread-safe implementation of a Last In, First Out (LIFO) cache.
 *
 * <p>The cache evicts the least recently added entry when the maximum size is reached.
 *
 * @param <K> Type of the keys.
 * @param <V> Type of the values.
 */
public class LIFOCache<K, V> implements Cache<K, V> {
    private final int maxSize;
    private final HashMap<K, V> cache;
    private final Stack<K> stack;
    private final Lock lock;

    /**
     * Constructs an LIFOCache with a specified maximum size.
     *
     * @param maxSize Maximum number of entries in the cache.
     * @throws IllegalArgumentException if maxSize is not positive.
     */
    public LIFOCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be greater than 0");
        }
        this.maxSize = maxSize;
        this.cache = new HashMap<>();
        this.stack = new Stack<>();
        this.lock = new ReentrantLock();
    }

    /**
     * Adds a key-value pair to the cache.
     *
     * @param key   The key.
     * @param value The value.
     * @throws IllegalArgumentException if key or value is null.
     */
    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value must not be null");
        }

        try {
            lock.lock();
            if (cache.size() >= maxSize) {
                K lastKey = stack.pop();
                cache.remove(lastKey);
            }

            cache.put(key, value);
            stack.push(key);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves the value associated with the given key from the cache.
     *
     * @param key The key to look up.
     * @return The value associated with the key, or null if the key is not present.
     * @throws IllegalArgumentException if key is null.
     */
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

    /**
     * Removes the entry associated with the given key from the cache.
     *
     * @param key The key to remove.
     * @throws IllegalArgumentException if key is null.
     */
    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        try {
            lock.lock();
            cache.remove(key);
            stack.remove(key);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Clears all entries from the cache.
     */
    @Override
    public void clear() {
        try {
            lock.lock();
            cache.clear();
            stack.clear();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the current size of the cache.
     *
     * @return The number of entries in the cache.
     */
    @Override
    public int size() {
        try {
            lock.lock();
            return cache.size();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Checks if the cache contains the specified key.
     *
     * @param key The key to check.
     * @return true if the key is present in the cache, false otherwise.
     * @throws IllegalArgumentException if key is null.
     */
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
