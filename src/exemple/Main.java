package exemple;

import cache.Cache;
import cache.CacheFactory;

public class Main {

    public static void main(String[] args) {
        testLRUCache();
        testLIFOCache();
        testFIFOCache();
    }

    private static void testLRUCache() {
        System.out.println("Testing LRUCache:");

        Cache<String, Integer> lruCache = CacheFactory.getCache(Cache.Algorithm.LRU, 3);

        lruCache.put("one", 1);
        lruCache.put("two", 2);
        lruCache.put("three", 3);

        System.out.println("Value for key 'one': " + lruCache.get("one"));
        System.out.println("Value for key 'two': " + lruCache.get("two"));
        System.out.println("Value for key 'three': " + lruCache.get("three"));

        lruCache.put("four", 4);
        System.out.println("Value for key 'one' after eviction: " + lruCache.get("one"));

        lruCache.clear();
        System.out.println("LRUCache size after clear: " + lruCache.size());
    }

    private static void testLIFOCache() {
        System.out.println("\nTesting LIFOCache:");

        Cache<String, Integer> lifoCache = CacheFactory.getCache(Cache.Algorithm.LIFO, 3);

        lifoCache.put("one", 1);
        lifoCache.put("two", 2);
        lifoCache.put("three", 3);

        System.out.println("Value for key 'one': " + lifoCache.get("one"));
        System.out.println("Value for key 'two': " + lifoCache.get("two"));
        System.out.println("Value for key 'three': " + lifoCache.get("three"));

        lifoCache.put("four", 4);
        System.out.println("Value for key 'one' after eviction: " + lifoCache.get("one"));

        lifoCache.clear();
        System.out.println("LIFOCache size after clear: " + lifoCache.size());
    }

    private static void testFIFOCache() {
        System.out.println("\nTesting FIFOCache:");

        Cache<String, Integer> fifoCache = CacheFactory.getCache(Cache.Algorithm.FIFO, 3);

        fifoCache.put("one", 1);
        fifoCache.put("two", 2);
        fifoCache.put("three", 3);

        System.out.println("Value for key 'one': " + fifoCache.get("one"));
        System.out.println("Value for key 'two': " + fifoCache.get("two"));
        System.out.println("Value for key 'three': " + fifoCache.get("three"));

        fifoCache.put("four", 4);
        System.out.println("Value for key 'one' after eviction: " + fifoCache.get("one"));

        fifoCache.clear();
        System.out.println("FIFOCache size after clear: " + fifoCache.size());
    }
}