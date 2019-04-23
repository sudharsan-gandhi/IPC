
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.lang.Math;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

class Producer {
    static int count = 0;
    static Integer loop = 100;
    static File file = new File("output.txt");
    static FileWriter fw = null;

    public static void generate() {
        try {
            for (int i = 0; i < Producer.loop; i++) {
                int temp = (int) (Math.random() * 100);
                System.out.println("appending new random value to file" + temp);
                Producer.fw.write(temp + " ");
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
            System.out.println(this.getName() + "reading data from file");
            BufferedReader reader = new BufferedReader(new FileReader("/home/sudharsan/waqar_algae/semaphores/output.txt")); // give full path mostly creates issues based on OS used.
            String text = null;

            while ((text = reader.readLine()) != null) {
                System.out.println(text);
            }
        } catch (Exception exc) {
            System.out.println(exc);
        }
        System.out.println(this.getName() + " releases the permit.");
        sem.release();
    }

}

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Semaphore sem = new Semaphore(1); // only one consumer allowed at a time
        try {
            Producer.fw = new FileWriter(Producer.file);
        } catch (FileNotFoundException fe) {
            System.out.println("File not found... try writing to the critical memory first");
        }
        Producer.generate();
        HandlerThread consumer1 = new HandlerThread(sem, "CONSUMER1");
        HandlerThread consumer2 = new HandlerThread(sem, "CONSUMER2");
        consumer1.start();
        consumer2.start();
        consumer1.join();
        consumer2.join();
        Producer.fw.close();
    }
}
