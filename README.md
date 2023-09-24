# Cache-Master
Welcome to CacheMaster, the ultimate caching repository! 🚀 Handle LIFO, LRU, and FIFO caching policies with top-notch performance. Boost your application's speed effortlessly.



Create Cache Instances
  FIFO Cache
  
      Cache<Integer, String> cache = CacheFactory.getCache(Cache.Algorithm.FIFO);
  LIFO Cache

      Cache<Integer, String> cache = CacheFactory.getCache(Cache.Algorithm.LIFO);
  LRU Cache
  
      Cache<Integer, String> cache = CacheFactory.getCache(Cache.Algorithm.LRU);

Example Usage
Here's an example of how to use the LRU caches:

    
    Cache<Integer, String> cache = CacheFactory.getCache(Cache.Algorithm.LRU);

    // Add data to the cache
    cache.put("key1", 1);
    cache.put("key2", 2);

    // Retrieve data from the cache
    int value = cache.get("key1"); // Retrieves 1

    // Delete data from the cache
    cache.delete("key1");

    // Check if a key exists in the cache
    boolean containsKey = cache.containsKey("key1"); // Returns false



Adding, Retrieving, and Deleting Data
You can use the following methods to interact with the cache:

put(K key, V value): Add a key-value pair to the cache.
get(K key): Retrieve the value associated with a key from the cache.
delete(K key): Remove a key-value pair from the cache.
clear(): Clear all data from the cache.
size(): Get the current number of elements in the cache.
containsKey(K key): Check if a key exists in the cache.
