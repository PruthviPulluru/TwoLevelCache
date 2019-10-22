package com.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class FileSystemCacheTest {
	private FileSystemCache fileSystem;

	private String inputObject = new String("fileSystem");

    @Before
    public void init() {
        fileSystem = new FileSystemCache(5);
    }

    @Test
    public void isEmpty() {
    	for(int i=0;i<4;i++){
    		 fileSystem.put(i+"", "value " + i);
    	}
        assertTrue(fileSystem.isEmpty());
        fileSystem.put("6", "value");
        assertFalse(fileSystem.isEmpty());
    }
    
    @Test
    public void checkInputObjectIsExistOrNot() {
        fileSystem.put("0", inputObject);
        assertEquals(inputObject, fileSystem.get("0"));
        assertNull(fileSystem.get("4242"));
    }

    @Test
    public void isDataPresent() {
        assertFalse(fileSystem.isDataPresent("0"));
        fileSystem.put("0", inputObject);
        assertTrue(fileSystem.isDataPresent("0"));
    }

  

}
