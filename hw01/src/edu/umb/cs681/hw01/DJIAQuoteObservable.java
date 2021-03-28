package edu.umb.cs681.hw01;

public class DJIAQuoteObservable extends Observable{
	
	float quote;
	public void changeQuote(float q) {
		this.quote = q;
		setChanged();
		notifyObservers(new DJIAEvent(q));
	}
}
