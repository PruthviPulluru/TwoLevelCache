package com.cache;

import java.util.HashMap;
import java.util.Map;


class InMemoryCache implements TwoLevelCache {
	private final Map<String, Object> inMemoryCache;
    private final int capacity;

    InMemoryCache(int capacity) {
        this.capacity = capacity;
        this.inMemoryCache = new HashMap<>(capacity);
    }

    @Override
    public Object get(String key) {
        return inMemoryCache.get(key);
    }

    @Override
    public void put(String key, Object value) {
    	if(isEmpty()){
    		inMemoryCache.put(key, value);
    	}else{
    		System.out.println("Not possible to put the data into InMemory");
    	}
    }

    public boolean isEmpty() {
        return inMemoryCache.size() < this.capacity;
    }
    
    public boolean isDataPresent(String key) {
        return inMemoryCache.containsKey(key);
    }

}
