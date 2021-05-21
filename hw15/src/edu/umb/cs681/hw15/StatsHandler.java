package edu.umb.cs681.hw15;

public class StatsHandler implements Runnable {
	private AdmissionMonitor monitor;
	private volatile boolean done;

	public StatsHandler(AdmissionMonitor monitor) {
		this.monitor = monitor;
	}

	public void setDone() {
		this.done = true;
	}

	public void run() {

		for (int i = 0; i < 5; i++) {
			if (done) {
				System.out.println("Stats handler interrupted!");
				break;
			}
			try {
				monitor.countCurrentVisitors();
				Thread.sleep(1000);
			} catch (InterruptedException exception) {
				System.out.println(exception.toString());
				continue;
			}
		}
	}

}
