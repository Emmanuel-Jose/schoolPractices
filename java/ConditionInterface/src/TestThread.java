/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chrio
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {

    public static void main(String[] args) throws InterruptedException {
        ItemQueue itemQueue = new ItemQueue(12);

        //Create a createTask and a runTask.
        Thread createTask = new generateTask(itemQueue);
        Thread runTask = new runTask(itemQueue);

        //Start both threads.
        createTask.start();
        runTask.start();

        //Wait for both threads to terminate.
        createTask.join();
        runTask.join();
    }

    static class ItemQueue {

        private Object[] items = null;
        private int current = 0;
        private int placeIndex = 0;
        private int removeIndex = 0;

        private final Lock lock;
        private final Condition isEmpty;
        private final Condition isFull;

        public ItemQueue(int capacity) {
            this.items = new Object[capacity];
            lock = new ReentrantLock();
            isEmpty = lock.newCondition();
            isFull = lock.newCondition();
        }

        public void add(Object item) throws InterruptedException {
            lock.lock();

            while (current >= items.length) {
                isFull.await();
            }

            items[placeIndex] = item;
            placeIndex = (placeIndex + 1) % items.length;
            ++current;

            //Notify the runTask that there is data available.
            isEmpty.signal();
            lock.unlock();
        }

        public Object remove() throws InterruptedException {
            Object item = null;

            lock.lock();

            while (current <= 0) {
                isEmpty.await();
            }
            item = items[removeIndex];
            removeIndex = (removeIndex + 1) % items.length;
            --current;

            //Notify the createTask that there is space available.
            isFull.signal();
            lock.unlock();

            return item;
        }

        public boolean isEmpty() {
            return (current == 0);
        }
    }

    static class generateTask extends Thread {

        private final ItemQueue queue;

        public generateTask(ItemQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String[] tasksList
                    = {"T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9",
                        "T10", "T11", "T12"};

            try {

                for (String task : tasksList) {
                    System.out.println("[Generating Task]: " + task);
                    queue.add(task);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    static class runTask extends Thread {

        private final ItemQueue queue;

        public runTask(ItemQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            try {

                do {
                    Object number = queue.remove();
                    System.out.println("[Running Task]: " + number);

                    if (number == null) {
                        return;
                    }
                } while (!queue.isEmpty());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("All threads ended successfully!");
            }
        }
    }
}
