/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chrio
 */
class RunnableDemo implements Runnable {

    int counter;
    ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<Integer>();

    public void run() {
        counter++;

        if (threadLocalCounter.get() == null) {
            threadLocalCounter.set(0);
        }
        threadLocalCounter.set(threadLocalCounter.get() + 1);
        threadLocalCounter.set(threadLocalCounter.get() + 1);
        threadLocalCounter.set(threadLocalCounter.get() + 1);
        threadLocalCounter.set(threadLocalCounter.get() + 1);

        System.out.println("Counter: " + counter);
        System.out.println("threadLocalCounter: " + threadLocalCounter.get());
    }
}

public class TestThread {

    public static void main(String[] args) {
        RunnableDemo commonInstance = new RunnableDemo();

        Thread t1 = new Thread(commonInstance);
        Thread t2 = new Thread(commonInstance);
        Thread t3 = new Thread(commonInstance);
        Thread t4 = new Thread(commonInstance);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // wait for threads to end
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}
