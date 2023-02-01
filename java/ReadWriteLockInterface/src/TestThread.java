/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jose
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestThread {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    static File file;

    private static void createFile() throws IOException {
        file = new File("archivo.txt");
//        myWriter = new FileWriter(file);
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

    private static void writeFile( int randomNumber )  {
        System.out.println(randomNumber);
        String message = getMessage( randomNumber );
        try {
            FileWriter myWriter = new FileWriter(file, true);
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
        Thread t2 = new Thread(new Writer());
        Thread t3 = new Thread(new Writer());
        Thread t4 = new Thread(new Writer());

        t1.setName("Writer A");
        t2.setName("Writer B");
        t3.setName("Writer C");
        t3.setName("Writer D");

        t1.start();
        t2.start();
        t3.start();
        t4.start();


    }


    static class Writer implements Runnable {

        public void run() {
            lock.writeLock().lock();
            try{
                int randomNumber = (int) (Math.random() * 8) + 1;
                writeFile( randomNumber );
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

}
