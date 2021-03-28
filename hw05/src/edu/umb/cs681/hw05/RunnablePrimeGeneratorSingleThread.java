package edu.umb.cs681.hw05;

public class RunnablePrimeGeneratorSingleThread extends PrimeGenerator implements Runnable {
	
	public RunnablePrimeGeneratorSingleThread(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}
	
	public static void main(String[] args) {
		
		RunnablePrimeGenerator g = new RunnablePrimeGenerator(1L,  2000000L);
		Thread t = new Thread(g);
        long startTime = System.currentTimeMillis();
        
        t.start();
        try{
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Number of prime numbers between 1 and 2,000,000: " + g.getPrimes().size());
        System.out.println("Single thread run time: " + (endTime - startTime));
	}
}
