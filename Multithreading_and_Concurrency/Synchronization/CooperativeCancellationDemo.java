package Multithreading_and_Concurrency.Synchronization;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// Yahan do alag tools hain lock & scheduler, kyunki do alag problems solve karni hain.
// - ReentrantLock: decide karta hai ki lock ek waqt par kis thread ke paas hai.
// - ScheduledExecutorService: future mein kisi particular time par task execute karne deta hai.
// Socho, agar sirf lock hota toh woh access control kar leta, lekin 3 seconds baad timeout action trigger karne ke liye alag mechanism chahiye hota.
// Aur agar sirf scheduler hota, toh woh timer chala sakta tha, lekin threads ke beech exclusive access control nahi karta.
class CooperativeTimedLock {
    private final ReentrantLock lock = new ReentrantLock();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public boolean tryLock() {
        return lock.tryLock();
    }

    public void unlockByOwner() {
        if (!lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException("Lock can only be unlocked by its owner");
        }

        lock.unlock();
        System.out.println("Lock released by " + Thread.currentThread().getName());
    }

    public ScheduledFuture<?> scheduleInterruption(Thread owner, long timeoutMillis) {
        return scheduler.schedule(() -> {
            System.out.println("Timeout reached - interrupting the owner.");
            owner.interrupt();
        }, timeoutMillis, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        scheduler.shutdownNow();
    }

}

public class CooperativeCancellationDemo {
    public static void main(String[] args) throws InterruptedException {
        CooperativeTimedLock timedLock = new CooperativeTimedLock();

        Thread idleUser = new Thread(() -> {
            if (!timedLock.tryLock()) {
                System.out.println("IdleUser could not acquire the lock...");
                return;
            }

            ScheduledFuture<?> timeoutTask = timedLock.scheduleInterruption(Thread.currentThread(), 2000);

            try {
                System.out.println("IdleUser acquired the lock and is now idle.");
                Thread.sleep(5000);
                System.out.println("IdleUser completed before the timeout.");

            } catch (InterruptedException e) {
                System.out.println("IdleUser observed the timeout request.");
                Thread.currentThread().interrupt();
            } finally {
                // timeoutTask us scheduled task ka handle hai, jiske through hum uski
                // cancellation request kar sakte hain.
                // Imagine karo IdleUser ne apna kaam 3 seconds se pehle hi complete kar liya.
                // Ab timeout ki koi need nahi hai. Agar scheduled task ko cancel nahi kiya, toh
                // woh baad mein execute ho sakta hai aur IdleUser ko unnecessarily interrupt
                // kar sakta hai.
                // cancel(false) Task ko cancel karo aur already running task ko interrupt mat
                // karo.
                // cancel(true) Task ko cancel karo aur agar woh already running hai, toh use
                // interrupt karne ki request bhi bhejo.
                timeoutTask.cancel(false);
                timedLock.unlockByOwner();
            }

        }, "IdleUser");

        Thread activeUser = new Thread(() -> {
            try {
                Thread.sleep(1000);

                while (!timedLock.tryLock()) {
                    System.out.println("ActiveUser still waiting...");
                    Thread.sleep(1000);
                }

                try {
                    System.out.println("ActiveUser acquired the lock and booked!");
                } finally {
                    // Executed by ActiveUser, which owns the lock here.
                    timedLock.unlockByOwner();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "ActiveUser");

        idleUser.start();
        activeUser.start();

        idleUser.join();
        activeUser.join();
    }
}
