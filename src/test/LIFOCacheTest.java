package test;

import cache.Cache;
import cache.CacheFactory;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * JUnit test class for LIFOCache.
 */
public class LIFOCacheTest {

    /**
     * Tests basic operations and eviction policy of LIFOCache.
     */
    @Test
    public void testLIFOCache() {
        // Initialize LIFOCache with a maximum size of 3
        Cache<String, Integer> lifoCache = CacheFactory.getCache(Cache.Algorithm.LIFO, 3);

        // Test put and get operations
        lifoCache.put("one", 1);
        lifoCache.put("two", 2);
        lifoCache.put("three", 3);

        assertEquals(Optional.of(1), lifoCache.get("one"));
        assertEquals(Optional.of(2), lifoCache.get("two"));
        assertEquals(Optional.of(3), lifoCache.get("three"));

        // Test eviction policy (LIFO)
        lifoCache.put("four", 4);
        assertNull(lifoCache.get("three")); // "three" should be evicted

        // Test clear operation
        lifoCache.clear();
        assertEquals(0, lifoCache.size());
        assertNull(lifoCache.get("two"));
    }
}
