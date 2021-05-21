package edu.umb.cs681.hw11;

public class ConcurrentSingletonMultiThreadTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(ConcurrentSingleton.getInstance());
        Thread t2 = new Thread(ConcurrentSingleton.getInstance());
        Thread t3 = new Thread(ConcurrentSingleton.getInstance());
        t1.start();
        t2.start();
        t3.start();
    }
}
