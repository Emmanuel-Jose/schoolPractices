/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chrio
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintDemo {

    private final Lock queueLock = new ReentrantLock();

    public void print() {
        queueLock.lock();

        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName()
                    + " Time Taken " + (duration / 1000) + " seconds.");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " printed the document successfully.");
            queueLock.unlock();
        }
    }
}

class ThreadDemo extends Thread {

    PrintDemo printDemo;

    ThreadDemo(String name, PrintDemo printDemo) {
        super(name);
        this.printDemo = printDemo;
    }

    @Override
    public void run() {
        System.out.printf(
                "%s starts printing a document\n", Thread.currentThread().getName());
        printDemo.print();
    }
}

public class TestThread {

    public static void main(String args[]) {
        PrintDemo PD = new PrintDemo();

        ThreadDemo t1 = new ThreadDemo("Thread - 1 ", PD);
        ThreadDemo t2 = new ThreadDemo("Thread - 2 ", PD);
        ThreadDemo t3 = new ThreadDemo("Thread - 3 ", PD);
        ThreadDemo t4 = new ThreadDemo("Thread - 4 ", PD);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
