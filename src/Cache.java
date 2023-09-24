public interface Cache<K, V> {

    enum Algorithm {

        LRU {
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

        /**
         * Create an instance of {@link Cache}
         *
         * @param maxSize the max size of the cache
         * @param <K>     the key type parameter
         * @param <V>     the value type parameter
         * @return the instance of {@link Cache}
         */
        abstract <K, V> Cache<K, V> create(int maxSize);
    }

    /**
     * Caches value for key.
     *
     * @param key   the key
     * @param value the value
     */
    void put(K key, V value);

    /**
     * Returns the value for key or null if it doesn't exist
     *
     * @param key the key for the value
     * @return the value
     */
    V get(K key);

    /**
     * Removes all the values in the cache
     */
    void clear();

    /**
     * Returns the number of values in currently in the cache
     *
     * @return the number of elements
     */
    int size();

    /**
     * Returns true if this cache contains a mapping for the specified key.
     *
     * @param key the key to test
     * @return true if the cache contains a value for the specified key
     */
    boolean containsKey(K key);

}