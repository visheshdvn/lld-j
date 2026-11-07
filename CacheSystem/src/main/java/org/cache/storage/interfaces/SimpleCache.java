package org.cache.storage.interfaces;

public interface SimpleCache<K, V> {
    void put(K key, V value) throws Exception;

    V get(K key) throws Exception;

    V remove(K key) throws Exception;

    boolean containsKey(K key) throws Exception;

    int size() throws Exception;

    int getCapacity();
}
