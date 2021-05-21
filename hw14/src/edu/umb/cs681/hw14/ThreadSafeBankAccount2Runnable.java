package edu.umb.cs681.hw14;

public class ThreadSafeBankAccount2Runnable {
    public static void main(String[] args) {
        ThreadSafeBankAccount2 account = new ThreadSafeBankAccount2();
        DepositRunnable deposit= new DepositRunnable(account);
        WithdrawRunnable withdrawal = new WithdrawRunnable(account);
        Thread[] dtArr = new Thread[15];
        Thread[] wtArr = new Thread[15];
        for (int i = 0; i < 15; i++) {
            Thread dt = new Thread(deposit);
            dt.start();
            dtArr[i] = dt;
            Thread wt = new Thread(withdrawal);
            wt.start();
            wtArr[i] = wt;
        }
        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }
        deposit.setDone();
        withdrawal.setDone();
        for (int i = 0; i < 15; i++) {
            dtArr[i].interrupt();
            wtArr[i].interrupt();
            try {
            	dtArr[i].join();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
