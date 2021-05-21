package edu.umb.cs681.hw15;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class AdmissionMonitor {
	private ReentrantLock lock = new ReentrantLock();
	private int currentVisitors = 0;
	private Condition visitorLimitExceed = lock.newCondition();
	private Condition minVisitorCondition = lock.newCondition();
	private volatile boolean done = false;

	public void enter() {
		lock.lock();
		try {
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + " Current visitor count: " + this.currentVisitors);
			while (currentVisitors >= 5) {

				if (done) {
					System.out.println("Interrupted!");
					break;
				}
				System.out.println("Maximum visitor count reached! Please wait for someone to exit");
				visitorLimitExceed.await();
				Thread.sleep(1000);
			}

			currentVisitors++;
			System.out.println(Thread.currentThread().getId() + " New visitors: " + this.currentVisitors);
			minVisitorCondition.signalAll();
		} 
		catch (InterruptedException exception) {
			System.out.println(exception);
		} finally {
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public void setDone() {
		done = true;
	}

	public void exit() {
		lock.lock();
		try {
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + " Current visitor count: " + this.currentVisitors);
			while (currentVisitors <= 0) {

				if (done) {
					System.out.println("Interrupted!");
					break;
				}
				System.out.println("No visitors! Please wait for someone to enter");
				minVisitorCondition.await();
				Thread.sleep(1000);
			}

			currentVisitors--;
			System.out.println(Thread.currentThread().getId() + " New visitors: " + this.currentVisitors);
			visitorLimitExceed.signalAll();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public int countCurrentVisitors() {
		return this.currentVisitors;
	}

}
