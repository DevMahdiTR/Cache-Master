import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class FIFOCache<K, V> implements Cache<K, V> {
    private final int maxSize;
    private final Map<K, V> cache;
    private final Queue<K> queue;

    public FIFOCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new HashMap<>();
        this.queue = new LinkedList<>();
    }

    @Override
    public void put(K key, V value) {
        if (cache.size() >= maxSize) {
            K oldestKey = queue.poll(); // Remove the oldest key from the queue
            if (oldestKey != null) {
                cache.remove(oldestKey); // Remove the corresponding entry from the cache
            }
        }

        cache.put(key, value);
        queue.offer(key); // Add the new key to the end of the queue
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void delete(K key) {
        cache.remove(key);
        queue.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
        queue.clear();
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }
}
