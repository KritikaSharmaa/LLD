package Multithreading_and_Concurrency;

class SendSMSRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(2000); // Delay of 2 seconds
            System.out.println("SMS Sent!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class sendEmailRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(3000); // Delay of 3 seconds
            System.out.println("Email Sent!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class calculateETA implements Runnable {
    public void run() {
        try {
            Thread.sleep(5000); // Delay of 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ETA Calculated: " + "25 mins");
    }
}

public class ThreadSolutionTwo {
    public static void main(String[] args) {
        try {
            Runnable smsRunnable = new SendSMSRunnable();
            Runnable emailRunnable = new sendEmailRunnable();
            Runnable etaRunnable = new calculateETA();

            Thread t1 = new Thread(smsRunnable);
            Thread t2 = new Thread(emailRunnable);
            Thread t3 = new Thread(etaRunnable);

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            System.out.println("Main method finished!");
            
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e);
        }
    }
}
