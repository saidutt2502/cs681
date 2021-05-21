package edu.umb.cs681.hw15;

public class ExitHandler implements Runnable {
	private AdmissionMonitor monitor;
	private volatile boolean done;

	public ExitHandler(AdmissionMonitor monitor) {
		this.monitor = monitor;
	}

	public void setDone() {
		this.done = true;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			if (done) {
				System.out.println("Exit handler interrupted!");
				break;
			}
			try {
				monitor.exit();
				Thread.sleep(1000);
			} catch (InterruptedException exception) {
				System.out.println(exception.toString());
				continue;
			}
		}
	}

}
