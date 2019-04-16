
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.lang.Math;

class Producer {
    static int count = 0;
    static LinkedList list = new LinkedList();
    static Integer loop = 100;

    public static void generate() {
        try {
            for(int i=0; i< Producer.loop; i++) {
                Producer.list.add(Math.random());
            }
            
        } catch (Exception exc) { 
            System.out.println(exc); 
        } 
    }
}

class HandlerThread extends Thread {
    Semaphore sem;
    Random random = new Random();

    public HandlerThread(Semaphore sem, String threadName) {
        super(threadName);
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            sem.acquire(); // semaphore activates and thread waits till it gets released
            for (int i = 0; i < Producer.loop; i++) {
                System.out.println(this.getName() + "reading value from position " + i + " is" + Producer.list.get(i));
            }

        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        System.out.println(this.getName() + " releases the permit.");
        sem.release();
    }

}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(1); // only one consumer allowed at a time
        Producer.generate();
        HandlerThread consumer1 = new HandlerThread(sem, "CONSUMER1");
        HandlerThread consumer2 = new HandlerThread(sem, "CONSUMER2");
        consumer1.start();
        consumer2.start();
        consumer1.join();
        consumer2.join();
    }
}
