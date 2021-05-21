package edu.umb.cs681.hw16;

import java.util.Random;

public class MyExampleObservable {
	
	public static void main(String args[]) {
		Random random = new Random();
		
        StockQuoteObservable stock = new StockQuoteObservable();
        String code = "STOCK";
        Float value = 2000.0f;
        
        DJIAQuoteObservable djia = new DJIAQuoteObservable();
    	Float val = 5000.0f;
        
        stock.addObserver(( Observable o, Object obj)-> {
        	String ticker = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println("Stock observer 1 has been added " + ticker + ": " + quote);
		});
        
        stock.addObserver(( Observable o, Object obj)-> {
        	String ticker = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println("Stock observer 2 has been added " + ticker + " " + quote);
		});
        
        System.out.println("Stock observer count: " + stock.countObservers());
   	 	stock.changeQuote(code, value);
   	 	value = 100.0f;
        System.out.println("\n Stock changed");
        stock.changeQuote(code, value);
        
        
    	djia.addObserver((Observable o, Object obj) -> {
            Float t = ((DJIAEvent) obj).getDjia();
            System.out.println("DJIA observer 1 has been added " + t);
        });
    	
    	djia.addObserver((Observable o, Object obj) -> {
            Float t = ((DJIAEvent) obj).getDjia();
            System.out.println("DJIA observer 2 has been added " + t);
        });
		
    	System.out.println("DJIA observer count: " + djia.countObservers()); 
    	djia.changeQuote(val);
    	val = 300.0f;
    	djia.changeQuote(val);
    	System.out.println("\n DJIA changed");
    	
    	
        Thread s1  = new Thread(() ->{ stock.changeQuote("STOCK", random.nextFloat()*10f + 1000f); 
        stock.notifyObservers(new StockEvent("STOCK", random.nextFloat()*10f + 1000f));});
        Thread s2  = new Thread(() ->{ stock.changeQuote("STOCK", random.nextFloat()*10f + 1000f); 
        stock.notifyObservers(new StockEvent("STOCK", random.nextFloat()*10f + 1000f));});
        Thread s3  = new Thread(() ->{ stock.changeQuote("STOCK", random.nextFloat()*10f + 1000f); 
        stock.notifyObservers(new StockEvent("STOCK", random.nextFloat()*10f + 1000f));});
        Thread s4  = new Thread(() ->{ stock.changeQuote("STOCK", random.nextFloat()*10f + 1000f); 
        stock.notifyObservers(new StockEvent("STOCK", random.nextFloat()*10f + 2000f));});
        

         Thread d1  = new Thread(() ->{ djia.changeQuote(random.nextFloat()*10f + 5000f); 
		   djia.notifyObservers(new DJIAEvent(random.nextFloat()*10f + 1000f));});
         Thread d2  = new Thread(() ->{ djia.changeQuote(random.nextFloat()*10f + 5000f); 
		   djia.notifyObservers(new DJIAEvent(random.nextFloat()*10f + 1000f));});
         Thread d3  = new Thread(() ->{ djia.changeQuote(random.nextFloat()*10f + 5000f); 
		   djia.notifyObservers(new DJIAEvent(random.nextFloat()*10f + 1000f));});
         Thread d4  = new Thread(() ->{ djia.changeQuote(random.nextFloat()*10f + 5000f); 
		   djia.notifyObservers(new DJIAEvent(random.nextFloat()*10f + 1000f));});
         
         
         s1.start();
         s2.start();
         s3.start();
         s4.start();
         
         
         d1.start();
         d2.start();
         d3.start();
         d4.start();
         
         
         try {
        	 s1.join();
        	 s2.join();
        	 s3.join();
        	 s4.join();
        	 
        	 
        	 d1.join();
        	 d2.join();
        	 d3.join();
        	 d4.join();
        	 
         }catch(InterruptedException e) {
        	 System.out.println(e);
         }
        
    }

}
