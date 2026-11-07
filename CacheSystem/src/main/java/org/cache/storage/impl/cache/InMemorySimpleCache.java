package org.cache.storage.impl.cache;

import org.cache.storage.interfaces.SimpleCache;

public class InMemorySimpleCache<K, V> implements SimpleCache<K, V> {
    @Override
    public void put(K key, V value) throws Exception {
        
    }

    @Override
    public V get(K key) throws Exception {
        return null;
    }

    @Override
    public V remove(K key) throws Exception {
        return null;
    }

    @Override
    public boolean containsKey(K key) throws Exception {
        return false;
    }

    @Override
    public int size() throws Exception {
        return 0;
    }

    @Override
    public int getCapacity() {
        return 0;
    }
}
