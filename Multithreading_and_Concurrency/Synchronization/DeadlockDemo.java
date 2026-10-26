package Multithreading_and_Concurrency.Synchronization;

class BankAccount {

    // Name helps us identify the account in logs
    private final String name;

    // Shared mutable state that needs protection
    private int balance;

    // Constructor – sets initial state
    public BankAccount(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    // Read-only helper used only for logging
    public String getName() {
        return name;
    }

    // Deposit is a critical section – guard with the object’s lock
    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}

class TransferTask implements Runnable {
    private final BankAccount from;
    private final BankAccount to;
    private final int amount;

    public TransferTask(BankAccount from, BankAccount to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        // First lock: 'from' account
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() +
                    " locked " + from.getName());

            // Artificial delay widens timing window
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }

            // Second lock: 'to' account - may block if another thread owns it
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() +
                        " locked " + to.getName());
                from.withdraw(amount);
                to.deposit(amount);
                System.out.println("Transferred " + amount + " from " +
                        from.getName() + " to " + to.getName());
            }
        }
    }
}

public class DeadlockDemo {
    public static void main(String[] args) throws InterruptedException {
        BankAccount accountA = new BankAccount("Account-A", 1000);
        BankAccount accountB = new BankAccount("Account-B", 1000);

        // T1 transfers A to B
        Thread t1 = new Thread(new TransferTask(accountA, accountB, 100), "T1");
        // T2 transfers B to A (reverse order - causes deadlock)
        Thread t2 = new Thread(new TransferTask(accountB, accountA, 200), "T2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // This line is never reached - deadlock occurred
        System.out.println("Both threads finished execution.");

    }
}
