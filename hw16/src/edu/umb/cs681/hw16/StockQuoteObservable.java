package edu.umb.cs681.hw16;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable{
	
	Map<String, Float> map = new HashMap<String, Float>();
	private ReentrantLock lock = new ReentrantLock();
	
	void changeQuote(String t,float q) {
		lock.lock();
		map.put(t, q);
		this.setChanged();
		this.notifyObservers(new StockEvent(t,q));
		lock.unlock();
	}
}
