package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	HashMap<java.nio.file.Path, Integer> map = new HashMap<java.nio.file.Path, Integer>();
    private static ReentrantLock staticLock = new ReentrantLock();
    private ReentrantLock nonStaticLock = new ReentrantLock();
    private static AccessCounter instance = null;

    private AccessCounter() {};

    public static AccessCounter getInstance() {
        staticLock.lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
            return instance;
        }
        finally {
            staticLock.unlock();
        }
    }

    public void increment(Path path) {
    	nonStaticLock.lock();
        try {
            if (map.containsKey(path)) {
                map.put(path, map.get(path) + 1);
            }
            else {
                map.put(path, 1);
            }
        }
        finally {
        	nonStaticLock.unlock();
        }
    }

    public int getCount(Path path) {
    	nonStaticLock.lock();
        try {
            if (map.containsKey(path)) {
                return map.get(path);
            }
            else {
                return 0;
            }
        }
        finally {
        	nonStaticLock.unlock();
        }
    }
}
