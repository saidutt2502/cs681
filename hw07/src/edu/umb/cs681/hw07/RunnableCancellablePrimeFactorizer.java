package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();

	public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}

	public void setDone() {
		lock.lock();
		try { done = false; } 
		finally { lock.unlock(); }
	}

	public void generatePrimeFactors() {
		
		long divisor = from;
		while (dividend != 1 && divisor <= to) {
			lock.lock();
			try {
				if (done) {	break; }
				if (divisor > 2 && isEven(divisor)) {
					divisor++;
					continue;
				}
				if (dividend % divisor == 0) {
					factors.add(divisor);
					dividend /= divisor;
				} 
				else if (divisor == 2) { divisor++; } 
				else { divisor += 2; }
			} 
			finally { lock.unlock(); }
		}
	}
	
	public static void main(String[] args) {
        System.out.println("Prime factors of 40 using a single thread");
        RunnableCancellablePrimeFactorizer r1 = new RunnableCancellablePrimeFactorizer(40, 2, (long) Math.sqrt(40));
        Thread t1 = new Thread(r1);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Prime factors of 2489 using two threads");
        RunnableCancellablePrimeFactorizer r2 = new RunnableCancellablePrimeFactorizer(2489, 2, (long) Math.sqrt(2489) / 2);
        RunnableCancellablePrimeFactorizer r3 = new RunnableCancellablePrimeFactorizer(2489, 1 + (long) Math.sqrt(2489) / 2, (long) Math.sqrt(2489));
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        t2.start();
        t3.start();
        try {
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Cancellable prime factors of 200");
        RunnableCancellablePrimeFactorizer r4 = new RunnableCancellablePrimeFactorizer(200, 2, 200);
        Thread t4 = new Thread(r4);
        t4.start();
        r4.setDone();
        try {
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}