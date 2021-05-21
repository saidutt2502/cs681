package edu.umb.cs681.hw18;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Observable {
	private ConcurrentLinkedQueue<Observer> observers;
	private AtomicBoolean changed;
	
	public Observable() {
		observers = new ConcurrentLinkedQueue<Observer>();
		changed = new AtomicBoolean();
		
	}
	
	public synchronized void addObserver(Observer obs) {
		if(obs == null) {
			throw new NullPointerException();
		}
		observers.add(obs);
    }
	
	public synchronized void deleteObserver(Observer obs) {
		if(observers.remove(obs)) {
			System.out.println("The observer has been deleted");
		}
	}
	
	public synchronized boolean hasChanged() {
		return changed.get();
    }
	
	protected synchronized void setChanged() {
		changed.set(true);
    }
	
	protected synchronized void clearChanged() {
		changed.set(false);
	}
	
	protected int countObservers() {
		return observers.size();
	}
	
	public void notifyObservers() {
		notifyObservers(null);
    }
	
	public void notifyObservers(Object obj) {
		if(!changed.get()) {
			return;
		}
		observers.forEach(o -> o.update(this, obj));
		clearChanged();
	}
	
}
