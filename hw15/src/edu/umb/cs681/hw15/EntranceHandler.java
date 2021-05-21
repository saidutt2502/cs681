package edu.umb.cs681.hw15;

public class EntranceHandler implements Runnable {
	private AdmissionMonitor monitor;
	private volatile boolean done;

	public EntranceHandler(AdmissionMonitor monitor) {
		this.monitor = monitor;
	}

	public void setDone() {
		this.done = true;
	}

	public void run() {

		for (int i = 0; i < 5; i++) {
			if (done) {
				System.out.println("Entrance handler interrupted!");
				break;
			}
			try {
				monitor.enter();
				Thread.sleep(1000);
			} catch (InterruptedException exception) {
				System.out.println(exception.toString());
				continue;
			}
		}

	}
}
