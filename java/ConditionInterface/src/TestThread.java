/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chrio
 */
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread extends javax.swing.JFrame {

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
                    = { "cmd.exe " + "/c " + "ipconfig",
                        "cmd.exe " + "/c " + "ping -n 3 google.com",
                        "cmd.exe " + "/c " + "Hostname",
                        "cmd.exe " + "/c " + "getmac",
                        "cmd.exe " + "/c " + "arp -a",
                        "cmd.exe " + "/c " + "tracert -d www.google.com",
                        "cmd.exe " + "/c " + "nslookup www.google.com",
                        "cmd.exe " + "/c " + "netstat",
                        "cmd.exe " + "/c " + "netsh",
                        "cmd.exe " + "/c " + "net",
                        "cmd.exe " + "/c " + "pathping www.google.com",
                        "cmd.exe " + "/c " + "systeminfo"
            };

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
        Runtime runtime  = Runtime.getRuntime();
        InputStream in = null;

        @Override
        public void run() {

            try {

                do {
                    Object task = queue.remove();
                    System.out.println("[Running Task]: " + task);

                    Process exec = runtime.exec((String) task);
                    in = exec.getInputStream();

                    int length = -1;
                    byte[] buffer = new byte[1024];
                    StringBuilder sb = new StringBuilder();

                    while ((length = in.read(buffer)) != -1) {
                        sb.append(new String(buffer, 0, length, "GBK"));
                    }

                    System.out.println(sb.toString());


                    if (task == null) {
                        return;
                    }
                } while (!queue.isEmpty());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("All threads ended successfully!");
            }
        }
    }
}
