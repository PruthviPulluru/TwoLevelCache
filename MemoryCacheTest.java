package com.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MemoryCacheTest {

	private InMemoryCache inMemory;

    private String inputObject = new String("inMemory");

    @Before
    public void init() {
        inMemory = new InMemoryCache(5);
    }

    @Test
    public void isEmpty() {
    	for(int i=0;i<4;i++){
    		inMemory.put(i+"", "value " + i);
    	}
        assertTrue(inMemory.isEmpty());
        inMemory.put("6", "value");
        assertFalse(inMemory.isEmpty());
    }
    
    @Test
    public void checkInputObjectIsExistOrNot() {
        inMemory.put("0", inputObject);
        assertEquals(inputObject, inMemory.get("0"));
        assertNull(inMemory.get("345"));
    }


    @Test
    public void isDataPresent() {
        assertFalse(inMemory.isDataPresent("2"));
        inMemory.put("2", inputObject);
        assertTrue(inMemory.isDataPresent("2"));
    }

}