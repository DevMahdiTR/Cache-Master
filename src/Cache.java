
public interface Cache<K, V> {

    enum Algorithm {
        LRU {
            @Override
            <K, V> Cache<K, V> create(int maxSize) {
                return new LRUCache<>(maxSize);
            }
        },
        FIFO {
            @Override
            <K, V> Cache<K, V> create(int maxSize) {
                return new FIFOCache<>(maxSize);
            }
        },
        LIFO {
            @Override
            <K, V> Cache<K, V> create(int maxSize) {
                return new LIFOCache<>(maxSize);
            }
        };

        abstract <K, V> Cache<K, V> create(int maxSize);
    }

    void put(K key, V value);

    V get(K key);

    void delete(K key);

    void clear();

    int size();

    boolean containsKey(K key);
}