package Multithreading_and_Concurrency;

class SendSMSThread extends Thread {
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

class calculateETA extends Thread {
    public void run() {
        try {
            Thread.sleep(5000); // Delay of 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ETA Calculated: " + "25 mins");
    }
}

public class ThreadSolutionOne {
    public static void main(String[] args) {
        try {
            Thread t1 = new SendSMSThread();
            Thread t2 = new sendEmailThread();
            Thread t3 = new calculateETA();

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();
            System.out.println("Main method finished!");
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }
}

//Agr hum join()[Line no-44,45,46] ko comment krde tab bhi output kuch asia milega why?jabki "Main method finished!" print hona chahiye the bas -->
// Main method finished!
// SMS Sent!
// Email Sent!
// ETA Calculated: 25 mis

//ANS:
//Kyunki Java mein normal threads, jinhe humne start() se start kiya hai, by default non-daemon threads hote hain.
//Jab tak ye threads apna kaam finish nahi karte, JVM normally band nahi hoti. Isliye join() remove karne ke baad bhi tumhe teeno messages dikh rahe hain.
//Matlab:
//- join() ke saath: main thread worker threads ke finish hone ka wait karta hai.
//- join() ke bina: main thread aage badh sakta hai, lekin JVM normal worker threads ke finish hone ka wait karti hai.
