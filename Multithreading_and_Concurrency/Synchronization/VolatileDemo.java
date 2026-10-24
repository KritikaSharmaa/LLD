package Multithreading_and_Concurrency.Synchronization;

class SharedData{
    volatile boolean flag = true;

    public void stop(){
        this.flag = false;
        System.out.println("Flag status: "+ this.flag +"XXXXXXX");
    }

    public boolean getFlag(){
        return flag;
    }
}

public class VolatileDemo {
    public static void main(String[] args){
        SharedData sharedData = new SharedData();
        Thread t1 = new Thread(()->{
            while (sharedData.getFlag()) {
                System.out.println("Flag Status: "+ sharedData.getFlag());
            }
        });

        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sharedData.stop();
        });

        t1.start();
        t2.start();
    }
}
