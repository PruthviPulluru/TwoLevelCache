package com.cache;

public interface TwoLevelCache {
    Object get(String key);
    void put(String key, Object value);
}
