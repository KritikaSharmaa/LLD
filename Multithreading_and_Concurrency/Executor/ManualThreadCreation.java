package Multithreading_and_Concurrency.Executor;

class MatchRider {
    public void requestRide(String riderId) {
        // Creating a new thread for the ride
        Thread matchThread = new Thread(() -> {
            System.out.println("Matching rider " + riderId + " to a driver...");
            // Simulate some processing
            try {
                Thread.sleep(1000); // Simulate a 1-second matching process
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Ride matched for rider " + riderId);
        });
        matchThread.start();
    }
}

public class ManualThreadCreation {
    public static void main(String[] args) {
        MatchRider rider = new MatchRider();
        rider.requestRide("kritika");
        rider.requestRide("anil");

        //Creating a new thread for each task can quickly lead to an excessive number of threads, overwhelming the system and degrading performance.
    }
}
