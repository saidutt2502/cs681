package edu.umb.cs681.hw11;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton implements Runnable{

    private ConcurrentSingleton(){};
    private static AtomicReference<ConcurrentSingleton> instance = null;

    public static ConcurrentSingleton getInstance() {

        if (instance == null) {
            instance = new AtomicReference<ConcurrentSingleton>(new ConcurrentSingleton());
        }
        return instance.get();

    }

    public void run() {
        System.out.println("Current thread:" + Thread.currentThread().getId() + "\nInstance: " + instance);
    }

}
