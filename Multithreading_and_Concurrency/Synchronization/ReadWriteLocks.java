package Multithreading_and_Concurrency.Synchronization;

import java.util.concurrent.locks.*;

class Library {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void updateBookShelf() {
        lock.writeLock().lock();
        try {
            System.out.println(
                    Thread.currentThread().getName()
                            + " updating bookshelf...");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void readBook() {
        lock.readLock().lock();
        try {
            System.out.println(
                    Thread.currentThread().getName()
                            + " reading book...");
        } finally {
            lock.readLock().unlock();
        }
    }
}

public class ReadWriteLocks {
    public static void main(String[] args) throws InterruptedException {
        Library library = new Library();
        Thread t1 = new Thread(() -> {
            library.readBook();
        });
        Thread t2 = new Thread(() -> {
            library.readBook();
        });
        Thread t3 = new Thread(() -> {
            library.updateBookShelf();
        });
        Thread t4 = new Thread(() -> {
            library.readBook();
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}
// t1, t2, t4 — read lock lete hain, toh ye simultaneously read kar sakte hain.
// t3 — write lock leta hai, toh jab tak readers read lock hold kar rahe hain,
// ise wait karna padega.
// Agar t3 pehle write lock le leta hai, toh baaki readers uske unlock karne tak
// wait karenge.