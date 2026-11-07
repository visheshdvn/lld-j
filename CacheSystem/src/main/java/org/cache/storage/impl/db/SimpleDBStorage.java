package org.cache.storage.impl.db;

import org.cache.storage.interfaces.DbStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleDBStorage<K, V> implements DbStorage<K, V> {
//    mock database using concurrent hashmap
    private final Map<K, V> db = new ConcurrentHashMap<>();

    @Override
    public void write(K key, V value) throws Exception {
        db.put(key, value);
    }

    @Override
    public V read(K key) throws Exception {
        if(!db.containsKey(key)) {
            throw new Exception("Read operation successful. Key not found in DB: " + key);
        }
        return db.get(key);
    }

    @Override
    public void delete(K key) throws Exception {
        if(!db.containsKey(key)) {
            throw new Exception("Delete operation unsuccessful. Key not found in DB: " + key);
        }
        db.remove(key);
    }
}
