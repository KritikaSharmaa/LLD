package Multithreading_and_Concurrency.Synchronization;

class CounterDemo {
    private int count = 0;

    public void increment() {
        this.count++;
    }

    public int getCount() {
        return this.count;
    }
}

public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        CounterDemo counter = new CounterDemo();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Result: " + counter.getCount());
    }
}
