public final class CacheFactory {

    private static final int DEFAULT_MAX_SIZE = 500;
    private static final Cache.Algorithm DEFAULT_ALGORITHM = Cache.Algorithm.LRU;

    private CacheFactory() {

    }

    /**
     * Creates a default {@link Cache} instance.
     *
     * @return the cache instance
     */
    public static <K, V> Cache<K, V> getCache() {
        return getCache(DEFAULT_ALGORITHM, DEFAULT_MAX_SIZE);
    }

    /**
     * Creates a {@link Cache} instance that uses the specified algorithm.
     *
     * @param algorithm the cache algorithm
     * @return the cache instance
     * @throws IllegalArgumentException if algorithm is null
     */
    public static <K, V> Cache<K, V> getCache(Cache.Algorithm algorithm) {
        return getCache(algorithm, DEFAULT_MAX_SIZE);
    }

    /**
     * Creates a {@link Cache} instance with the specified max size
     *
     * @param maxSize the maximum size
     * @return the cache instance
     * @throws IllegalArgumentException if maxSize isn't greater than 0
     */
    public static <K, V> Cache<K, V> getCache(int maxSize) {
        return getCache(DEFAULT_ALGORITHM, maxSize);
    }

    /**
     * Creates a {@link Cache} instance
     *
     * @param algorithm the cache algorithm
     * @param maxSize   the cache maximum size
     * @return the cache instance
     * @throws IllegalArgumentException if algorithm is null
     * @throws IllegalArgumentException if maxSize isn't greater than 0
     */
    public static <K, V> Cache<K, V> getCache(Cache.Algorithm algorithm, int maxSize) {
        if (algorithm == null)
            throw new IllegalArgumentException("algorithm must not be null");

        if (maxSize <= 0)
            throw new IllegalArgumentException("maxSize must be greater than 0");

        return algorithm.create(maxSize);
    }

}