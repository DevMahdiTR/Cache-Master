package cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LRUCache is a thread-safe implementation of a Least Recently Used (LRU) cache.
 *
 * <p>The cache evicts the least recently used entry when the maximum size is reached.
 *
 * @param <K> Type of the keys.
 * @param <V> Type of the values.
 */
public class LRUCache<K, V> implements Cache<K, V> {
    private final int maxSize;
    private final Map<K, V> cache;
    private final Lock lock;

    /**
     * Constructs an LRUCache with a specified maximum size.
     *
     * @param maxSize Maximum number of entries in the cache.
     * @throws IllegalArgumentException if maxSize is not positive.
     */
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
            cache.put(key, value);
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