package test;

import cache.Cache;
import cache.CacheFactory;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class LRUCacheTest {

    @Test
    void testLRUCache() {
        Cache<String, Integer> lruCache = CacheFactory.getCache(Cache.Algorithm.LRU, 3);

        // Test put and get operations
        lruCache.put("one", 1);
        lruCache.put("two", 2);
        lruCache.put("three", 3);

        assertEquals(Optional.of(1), lruCache.get("one"));
        assertEquals(Optional.of(2), lruCache.get("two"));
        assertEquals(Optional.of(3), lruCache.get("three"));

        // Test eviction policy (LRU)
        lruCache.put("four", 4);
        assertNull(lruCache.get("one")); // "one" should be evicted

        // Test clear operation
        lruCache.clear();
        assertEquals(0, lruCache.size());
        assertNull(lruCache.get("two"));
    }
}