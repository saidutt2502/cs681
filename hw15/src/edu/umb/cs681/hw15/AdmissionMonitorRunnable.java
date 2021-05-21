package edu.umb.cs681.hw15;

public class AdmissionMonitorRunnable {

	public static void main(String[] args) {

		AdmissionMonitor monitor = new AdmissionMonitor();

		EntranceHandler entrance = new EntranceHandler(monitor);
		ExitHandler exit = new ExitHandler(monitor);
		StatsHandler stat = new StatsHandler(monitor);

		Thread thread1;
		Thread thread2;
		Thread thread3;

		for (int i = 0; i < 5; i++) {

			thread1 = new Thread(entrance);
			thread2 = new Thread(exit);
			thread3 = new Thread(stat);

			thread1.start();
			thread2.start();
			thread3.start();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			entrance.setDone();
			exit.setDone();
			stat.setDone();
			
			thread1.interrupt();
			thread2.interrupt();
			thread3.interrupt();

			try {
				thread1.join();
				thread2.join();
				thread3.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
