import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LIFOCache<K, V> implements Cache<K, V> {
    private final int maxSize;
    private final Map<K, V> cache;
    private final Stack<K> stack;

    public LIFOCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new HashMap<>();
        this.stack = new Stack<>();
    }

    @Override
    public void put(K key, V value) {
        if (cache.size() >= maxSize) {
            K lastKey = stack.pop(); // Remove the last (most recently added) key from the stack
            cache.remove(lastKey); // Remove the corresponding entry from the cache
        }

        cache.put(key, value);
        stack.push(key); // Add the new key to the top of the stack
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void delete(K key) {
        cache.remove(key);
        stack.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
        stack.clear();
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
