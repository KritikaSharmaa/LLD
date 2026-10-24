package Multithreading_and_Concurrency.Synchronization;

import java.util.concurrent.locks.*;

class BookMyShow {
    private int availableSeats = 1;
    private final ReentrantLock lock = new ReentrantLock();

    public void bookSeat(String user) {
        System.out.println(user + " trying to acquire lock....");

        lock.lock();

        try {
            System.out.println(user+ " acquired the lock..");
            if (availableSeats >= 1) {
                availableSeats--;
                System.out.println(user+ " Your seat booked!!");
            }else{
                System.out.println(user +" Apologies! No seats available!!");
            }
        } finally {
            System.out.println(user+" Releasing acquired lock...");
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        BookMyShow bookMyShow = new BookMyShow();
        Thread t1 = new Thread(() -> {
            bookMyShow.bookSeat("kritika");
        });

        Thread t2 = new Thread(()->{
            bookMyShow.bookSeat("CH Sharma");
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
