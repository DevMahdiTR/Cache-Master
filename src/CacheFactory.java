public final class CacheFactory {

    private static final int DEFAULT_MAX_SIZE = 500;
    private static final Cache.Algorithm DEFAULT_ALGORITHM = Cache.Algorithm.LRU;

    private CacheFactory() {
    }

    public static <K, V> Cache<K, V> getCache() {
        return getCache(DEFAULT_ALGORITHM, DEFAULT_MAX_SIZE);
    }

    public static <K, V> Cache<K, V> getCache(Cache.Algorithm algorithm) {
        return getCache(algorithm, DEFAULT_MAX_SIZE);
    }

    public static <K, V> Cache<K, V> getCache(int maxSize) {
        return getCache(DEFAULT_ALGORITHM, maxSize);
    }

    public static <K, V> Cache<K, V> getCache(Cache.Algorithm algorithm, int maxSize) {
        if (algorithm == null) {
            throw new IllegalArgumentException("algorithm must not be null");
        }

        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be greater than 0");
        }

        return algorithm.create(maxSize);
    }
}