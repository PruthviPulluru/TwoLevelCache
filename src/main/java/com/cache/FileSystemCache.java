package com.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

class FileSystemCache implements TwoLevelCache {
	private Map<Object, String> fileSystemData = new HashMap<>(0);
    private int capacity = 0;
    private Path dir;

    FileSystemCache(int capacity) { // initialize the capacity
        try {
			this.dir = Files.createTempDirectory("cache"); // create the cache directory
			this.dir.toFile().deleteOnExit();
			
			this.capacity = capacity;
			this.fileSystemData = new HashMap<>(capacity);
		} catch (Exception e) {
			System.out.println("Error - while creating the cache directoy "+ e.getMessage());
		}
        
        
    }

    @Override
    public Object get(String key) {
        if (isDataPresent(key)) {
            String fileName = fileSystemData.get(key);
            try (FileInputStream fileInputStream = new FileInputStream(new File(dir + File.separator + fileName));
            	ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                return (String) objectInputStream.readObject();
            } catch (Exception e) {
            	System.out.println("Error - while reading the file. FileName : "+fileName + " Reason :"+e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void put(String key, Object value) {
    	if(!isEmpty()){
    		System.out.println("Not possible to put the data into cache");
    	}else{
    		try {
    			File tmpFile = Files.createTempFile(dir, "", "").toFile();
    			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(tmpFile));
    			outputStream.writeObject(value);
    			outputStream.flush();
    			fileSystemData.put(key, tmpFile.getName());
    			outputStream.close();
    		} catch (Exception e) {
    			System.out.println("Error - not able to read and write the cache file. Reason : "+ e.getMessage());
    		}
    	}

    }
  //Check cache data is empty or not
    public boolean isEmpty() {
        return fileSystemData.size() < this.capacity;
    }
//Check data is present in cache or not
	public boolean isDataPresent(String input) {
		return fileSystemData.containsKey(input);
	}

}
