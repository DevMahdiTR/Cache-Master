package test;

import cache.Cache;
import cache.CacheFactory;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * JUnit test class for FIFOCache.
 */
public class FIFOCacheTest {

    /**
     * Tests basic operations and eviction policy of FIFOCache.
     */
    @Test
    public void testFIFOCache() {
        // Initialize FIFOCache with a maximum size of 3
        Cache<String, Integer> fifoCache = CacheFactory.getCache(Cache.Algorithm.FIFO, 3);

        // Test put and get operations
        fifoCache.put("one", 1);
        fifoCache.put("two", 2);
        fifoCache.put("three", 3);

        assertEquals(Optional.of(1), fifoCache.get("one"));
        assertEquals(Optional.of(2), fifoCache.get("two"));
        assertEquals(Optional.of(3), fifoCache.get("three"));

        // Test eviction policy (FIFO)
        fifoCache.put("four", 4);
        assertNull(fifoCache.get("one")); // "one" should be evicted

        // Test clear operation
        fifoCache.clear();
        assertEquals(0, fifoCache.size());
        assertNull(fifoCache.get("two"));
    }
}
