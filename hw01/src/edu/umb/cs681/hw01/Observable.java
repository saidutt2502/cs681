package edu.umb.cs681.hw01;
import java.util.*;

public abstract class Observable {
	private LinkedList<Observer> observers = new LinkedList<Observer>();
	private boolean changed;
	
	public synchronized void addObserver(Observer obs) {
		observers.add(obs);
    }
	
	public synchronized void deleteObserver(Observer obs) {
		observers.remove(obs);
	}
	
	public synchronized boolean hasChanged() {
		return changed;
    }
	
	protected synchronized void setChanged() {
		changed = true;
    }
	
	protected synchronized void clearChanged() {
    	changed = false;
	}
	
	protected int countObservers() {
		return observers.size();
	}
	
	public void notifyObservers() {
		notifyObservers(null);
    }
	
	public void notifyObservers(Object obj) {
  		if (!hasChanged())
  			return;
		
	    observers.forEach((Observer observer) -> observer.update(this, obj));
	    clearChanged();
	}
}
