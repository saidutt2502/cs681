package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {

    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try { 
        	done = false; 
    	}
        finally { 
        	lock.unlock(); 
    	}
    }

    public void generatePrimeFactors() {

        long divisor = from;
        
        while (dividend != 1 && divisor <= to) {
            lock.lock();
            try {
                if (done) {
                    System.out.println("Stopped generating prime factors.");
                    this.factors.clear();
                    break;
                }
                if (divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }
                if (dividend % divisor == 0) {
                    System.out.println(divisor);
                    factors.add(divisor);
                    dividend /= divisor;
                }
                else if (divisor == 2) { divisor++; } 
				else { divisor += 2; }
            }
            finally { 
            	lock.unlock(); 
        	}
            try {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }

    public static void main(String[] args) {

        RunnableCancellableInterruptiblePrimeFactorizer gen = new
                RunnableCancellableInterruptiblePrimeFactorizer(36, 2, (long) Math.sqrt(36));

        Thread thread = new Thread(gen);
        thread.start();

        try {
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        gen.setDone();
        thread.interrupt();

        try {
            thread.join();
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("Factors of 36	:\t\t");
        gen.getPrimeFactors().forEach((Long factors) -> System.out.print(factors + ", "));

        RunnableCancellableInterruptiblePrimeFactorizer gen1 = new
                RunnableCancellableInterruptiblePrimeFactorizer(84, 2, (long) Math.sqrt(84) / 2);
        RunnableCancellableInterruptiblePrimeFactorizer gen2 = new
                RunnableCancellableInterruptiblePrimeFactorizer(84, 1 + (long) Math.sqrt(84) / 2,
        				(long) Math.sqrt(84));

        Thread thread1 = new Thread(gen1);
        Thread thread2 = new Thread(gen2);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gen1.setDone();
        gen2.setDone();
        thread1.interrupt();
        thread2.interrupt();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("Factors of 84 generated on thread 1 are	:\t\t");
        gen1.getPrimeFactors().forEach((Long factors) -> System.out.print(factors + ", "));
        System.out.println();

        System.out.print("Factors of 84 generated on thread 2 are	:\t\t");
        gen2.getPrimeFactors().forEach((Long factors) -> System.out.print(factors + ", "));
    }

}
