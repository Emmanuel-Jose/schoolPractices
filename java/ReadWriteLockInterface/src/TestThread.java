/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chrio
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestThread {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    static File file;
    static FileWriter myWriter;

    private static void createFile() throws IOException {
        file = new File("archivo.txt");
        myWriter = new FileWriter(file);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch ( IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void writeFile( int randomNumber ) throws InterruptedException {
        System.out.println(randomNumber);
        String message = getMessage( randomNumber );
        try {
            myWriter.write(message);
            myWriter.write("\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static String getMessage(int messageNumber) {
        return switch (messageNumber) {
            case 1 -> "Busy Port";
            case 2 -> "Port not reachable";
            case 3 -> "File does not exist";
            case 4 -> "Invalid path";
            case 5 -> "Server not responding";
            case 6 -> "Unexpected error";
            case 7 -> "Memory error";
            case 8 -> "Read error";
            default -> "Write error";
        };
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        createFile();

        Thread t1 = new Thread(new Writer());
        Thread t2 = new Thread(new WriterB());
        t1.setName("Writer A");
        t2.setName("Writer B");
        t1.start();
        t2.start();


    }


    static class Writer implements Runnable {

        public void run() {
            lock.writeLock().lock();
            try{
                int randomNumber = (int) (Math.random() * 8) + 1;
                writeFile( randomNumber );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    static class WriterB implements Runnable {

        public void run() {
            lock.writeLock().lock();
            try{
                int randomNumber = (int) (Math.random() * 8) + 1;
                writeFile( randomNumber );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }


//    static class WriterA implements Runnable {
//
//        public void run() {
//            lock.writeLock().lock();
//
//            try {
//                Long duration = (long) (Math.random() * 10000);
//                System.out.println(Thread.currentThread().getName()
//                        + " Time Taken " + (duration / 1000) + " seconds.");
//                Thread.sleep(duration);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                message = message.concat("a");
//                lock.writeLock().unlock();
//            }
//        }
//    }

}
