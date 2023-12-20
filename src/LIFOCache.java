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
            K lastKey = stack.pop(); 
            cache.remove(lastKey); 
        }

        cache.put(key, value);
        stack.push(key); 
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
