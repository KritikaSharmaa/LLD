package Multithreading_and_Concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class sendSMSThread extends Thread {
    public void run() {
        try {
            Thread.sleep(2000); // Delay of 2 seconds
            System.out.println("SMS Sent!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class sendEmailThread extends Thread {
    public void run() {
        try {
            Thread.sleep(3000); // Delay of 3 seconds
            System.out.println("Email Sent!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class calculateETA implements Callable<String> {

    @Override
    public String call() {
        try {
            Thread.sleep(5000); // Delay of 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ETA Calculated: 25 mins";
    }

}

public class ThreadSolutionCallable {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        sendSMSThread smsThread = new sendSMSThread();
        sendEmailThread emailThread = new sendEmailThread();
        calculateETA calculateETA = new calculateETA();

        executor.submit(smsThread);
        executor.submit(emailThread);
        Future<String> clacETA = executor.submit(calculateETA);

        try {
            System.out.println(clacETA.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}

//why executor.shutdown() important????

//Executor ke worker threads aam taur par non-daemon threads hote hain. 
//Agar executor ko shutdown nahi kiya, toh uska worker thread alive reh sakta hai aur JVM ko band hone se rok sakta hai.

// executor.shutdown() do important kaam karta hai:
// - Executor naye tasks accept karna band kar deta hai.
// - Pehle se submit kiye gaye tasks ko complete hone deta hai.
// Dhyan do: shutdown() ka matlab ye nahi ki saare tasks usi moment finish ho gaye. Ye unhe orderly completion ka mauka deta hai.