package edu.umb.cs681.hw17;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public void setDone() {
        lock.lock();
        try {
            done = true;
        }
        finally {
            lock.unlock();
        }
    }

    public void run() {

        String[] files = {"AccessCounter.class",
                "RequestHandler.class",
                "a.html",
                "b.html",
                "c.html"};
        AccessCounter acc = AccessCounter.getInstance();

        while (true) {
            lock.lock();
            try {
                if(done) {
                    System.out.println("File Access Revoked");
                    break;
                }

                int num = new Random().nextInt(files.length);
                Path path = FileSystems.getDefault().getPath(".", files[num]);

                acc.increment(path);
                System.out.println(files[num] + " \t\t\t: " + acc.getCount(path));
            }
            finally {
                lock.unlock();
            }

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }

    public static void main(String[] args) {

        RequestHandler r1  = new RequestHandler();
        RequestHandler r2  = new RequestHandler();
        RequestHandler r3  = new RequestHandler();
        RequestHandler r4  = new RequestHandler();
        RequestHandler r5  = new RequestHandler();
        RequestHandler r6  = new RequestHandler();
        RequestHandler r7  = new RequestHandler();
        RequestHandler r8  = new RequestHandler();
        RequestHandler r9  = new RequestHandler();
        RequestHandler r10 = new RequestHandler();
        RequestHandler r11 = new RequestHandler();

        Thread t1  = new Thread(r1);
        Thread t2  = new Thread(r2);
        Thread t3  = new Thread(r3);
        Thread t4  = new Thread(r4);
        Thread t5  = new Thread(r5);
        Thread t6  = new Thread(r6);
        Thread t7  = new Thread(r7);
        Thread t8  = new Thread(r8);
        Thread t9  = new Thread(r9);
        Thread t10 = new Thread(r10);
        Thread t11 = new Thread(r11);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();

        try {
            Thread.sleep(1000);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        r1.setDone();
        r2.setDone();
        r3.setDone();
        r4.setDone();
        r5.setDone();
        r6.setDone();
        r7.setDone();
        r8.setDone();
        r9.setDone();
        r10.setDone();
        r11.setDone();

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
        t4.interrupt();
        t5.interrupt();
        t6.interrupt();
        t7.interrupt();
        t8.interrupt();
        t9.interrupt();
        t10.interrupt();
        t11.interrupt();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();
            t11.join();
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
