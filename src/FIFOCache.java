class FIFOCache<K, V> implements Cache<K, V> {
    private final int maxSize;
    private final Map<K, V> cache;
    private final Queue<K> queue;
    private final Lock lock;

    public FIFOCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be greater than 0");
        }
        this.maxSize = maxSize;
        this.cache = new HashMap<>();
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
    }

    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value must not be null");
        }

        try {
            lock.lock();
            if (cache.size() >= maxSize) {
                K oldestKey = queue.poll();
                if (oldestKey != null) {
                    cache.remove(oldestKey);
                }
            }

            cache.put(key, value);
            queue.offer(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        try {
            lock.lock();
            return cache.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        try {
            lock.lock();
            cache.remove(key);
            queue.remove(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lock();
            cache.clear();
            queue.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        try {
            lock.lock();
            return cache.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        try {
            lock.lock();
            return cache.containsKey(key);
        } finally {
            lock.unlock();
        }
    }
}