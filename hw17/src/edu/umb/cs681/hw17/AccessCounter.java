package edu.umb.cs681.hw17;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	ConcurrentHashMap<java.nio.file.Path, AtomicInteger> map = new ConcurrentHashMap<java.nio.file.Path, AtomicInteger>();
    private static ReentrantLock staticLock = new ReentrantLock();
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
    	map.compute(path, (java.nio.file.Path k, AtomicInteger v) -> {
			if(v == null) {
				System.out.println(Thread.currentThread().getName() + " increased file " + path + " to " + 1);
				return new AtomicInteger(1);
			} else {
				System.out.println(Thread.currentThread().getName() + " increased file " + path + " to " + (v.get()+1));
				return new AtomicInteger(v.incrementAndGet());
			}
		});
    }

    public int getCount(Path path) {
    	return map.compute(path, (java.nio.file.Path k, AtomicInteger v) -> {
			if(v == null) {
				System.out.println(Thread.currentThread().getName() + " got file " + path + "; count : " + 0);
				return new AtomicInteger(0);
			} else {
				System.out.println(Thread.currentThread().getName() + " got file " + path + "; count : " + v.get());
				return v;
			}
		}).get();
    }
}
