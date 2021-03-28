package edu.umb.cs681.hw01;

public class MyExampleObservable {
	
	public static void main(String args[]) {
        StockQuoteObservable stock = new StockQuoteObservable();
        DJIAQuoteObservable djia = new DJIAQuoteObservable();
        
        stock.addObserver(( Observable o, Object obj)-> {
			System.out.println("Stock observer 1 had been added");
		});
		stock.changeQuote("Test1", 100);
			System.out.println("Stock observers have been notified");
			System.out.println("Stock observer count: "+stock.countObservers());
		
		stock.addObserver(( Observable o, Object obj)-> {
			System.out.println("Stock observer 2 has been added");
		});
		stock.changeQuote("Test2", 200);
		System.out.println("Stock observers have been notified");
		System.out.println("Stock observer count: "+stock.countObservers());
		
		djia.addObserver((Observable o, Object obj) -> {
            System.out.println("DJIA observer 1 had been added");
        });
        djia.addObserver((Observable o, Object obj) -> {
            System.out.println("DJIA observer 2 had been added");
        });
        djia.changeQuote(300);
        djia.changeQuote(400);
        
    }

}
