package Multithreading_and_Concurrency.Executor;

import java.util.concurrent.*;

class Emailer {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10); // Thread pool with 10 threads

    public void sendEmail(String mailto) {
        //executor.execute(runnable)
        //executor.submit(runnable | callable[return something and future will catch])
        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("exception: " + e);
            }

            System.out.println("email sent to: " + mailto);
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}

public class ExecutorServiceQ {
    public static void main(String[] args) {
        Emailer emailer = new Emailer();

        for (int i = 1; i < 6; i++) {
            emailer.sendEmail("user" + i + "@gmail.com");
        }

        emailer.shutdown();

    }
}