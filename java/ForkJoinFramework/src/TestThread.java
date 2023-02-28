/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chrio
 */
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TestThread {

    public static void main(final String[] arguments) throws InterruptedException,
            ExecutionException {

        int nThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Available Processors: " + nThreads);

        int number = 5;

        ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);
        Long result = forkJoinPool.invoke(new FactorialNumber(number));
        System.out.println(result);
    }

    static class FactorialNumber extends RecursiveTask<Long> {

        int number;

        FactorialNumber(int num) {
            this.number = num;
        }

        @Override
        protected Long compute() {
            if (number <= 1) {
                return 1L;
            } else {
                FactorialNumber factorialNumber = new FactorialNumber(number - 1);
                factorialNumber.fork();
                return number * factorialNumber.join();
            }
        }

    }
}
