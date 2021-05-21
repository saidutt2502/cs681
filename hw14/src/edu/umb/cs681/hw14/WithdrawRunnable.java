package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable {
    private ThreadSafeBankAccount2 account = null;
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public WithdrawRunnable(ThreadSafeBankAccount2 account) {
        this.account = account;
    }

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
        while (true) {
            lock.lock();
            try{
                if (done) {
                    System.out.println(Thread.currentThread().getId() + " executed");
                    break;
                }
            } 
            finally {
                lock.unlock();
            }
            account.withdraw(500);
            try{
                Thread.sleep(3000);
            } 
            catch (InterruptedException exception) {
                System.out.println(exception);
                continue;
            }
        }
    }
}
