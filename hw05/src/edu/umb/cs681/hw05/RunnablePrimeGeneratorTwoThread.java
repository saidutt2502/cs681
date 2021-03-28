package edu.umb.cs681.hw05;

public class RunnablePrimeGeneratorTwoThread extends PrimeGenerator implements Runnable {
	
	public RunnablePrimeGeneratorTwoThread(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}
	
	public static void main(String[] args) {
		
		RunnablePrimeGenerator g1 = new RunnablePrimeGenerator(1L, 1000000L);
        RunnablePrimeGenerator g2 = new RunnablePrimeGenerator(1000000L, 2000000L);
        Thread t1 = new Thread(g1);
        Thread t2 = new Thread(g2);
        long startTime = System.currentTimeMillis();
        
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Two thread run time : " + (endTime - startTime));
	}
}
