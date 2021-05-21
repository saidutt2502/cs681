package edu.umb.cs681.hw16;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
	private LinkedList<Observer> observers = new LinkedList<Observer>();;
	private AtomicBoolean changed= new AtomicBoolean(false);
	private ReentrantLock lock = new ReentrantLock();
	
	public synchronized void addObserver(Observer obs) {
		lock.lock();
		try {
			if(!observers.contains(obs)) {
				observers.add(obs);	
			}
		}
		finally {
			lock.unlock();
		}
    }
	
	public synchronized void deleteObserver(Observer obs) {
		lock.lock();
		try {
			if (observers.contains(obs)) {
				observers.remove(obs);
			} 
		} 
		finally {
			lock.unlock();
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
		lock.lock();
		try {
			return observers.size();
		} 
		finally {
			lock.unlock();
		}
	}
	
	public void notifyObservers() {
		notifyObservers(null);
    }
	
	public void notifyObservers(Object obj) {
		if(!changed.get()) {
			return;
		}
		lock.lock();
		try {
			observers.forEach(o -> o.update(this, obj));
		} 
		finally {
			lock.unlock();
		}
		clearChanged();
	}
	
}
