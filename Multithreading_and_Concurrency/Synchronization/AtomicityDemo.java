package Multithreading_and_Concurrency.Synchronization;

import java.util.concurrent.atomic.AtomicInteger;

class Bank {
    private AtomicInteger amount = new AtomicInteger(1000);

    public void withdraw(int withdraw_amt) {
        if (amount.get() >= withdraw_amt) {
            this.amount.set(amount.get() - withdraw_amt);
        }
    }

    public int checkBalance() {
        return amount.get();
    }
}

// Method Purpose
// amount.get() Gets the current int value
// amount.set(value) Sets a new value
// amount.addAndGet(value) Adds a value and returns the updated value
// amount.compareAndSet(expected, newValue) Updates only if the current value
// matches the expected value

public class AtomicityDemo {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();

        Runnable task = () -> {
            bank.withdraw(100);
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Current Balance post withdraw: " + bank.checkBalance());
    }
}
