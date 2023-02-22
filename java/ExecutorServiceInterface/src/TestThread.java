/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chrio
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestThread {

    public static void main(final String[] arguments) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            executor.submit(new Task());
            System.out.println("Shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {

            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

    static class Task implements Runnable {

        @Override
        public void run() {

            try {
                Long duration = (long) (Math.random() * 20);
                System.out.println("Running Task!");
                TimeUnit.SECONDS.sleep(duration);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
