import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

class AtomicVariables{
    Random rand = new Random();
    AtomicInteger[] numbers = new AtomicInteger[1000];
    double mean;
    double median;
    double stdDev;

    AtomicVariables(){
        generateRandomNumbers();
    }

    private void generateRandomNumbers() {
        // generate 1000 random numbers between 5 and 500
        for ( int i = 0; i < numbers.length; i++ ) {
            int randomNumber = rand.nextInt( 496 ) + 5;
            numbers[i] = new AtomicInteger(randomNumber);
        }
        System.out.println(Arrays.toString(numbers));
    }

    public void mean(){
        double sum = 0.0;
        for (AtomicInteger number : numbers) {
            sum += number.get();
        }
        mean = sum / numbers.length;

        System.out.println("Mean of the generated numbers: " + mean);
    }

    public void median(){
        Arrays.sort(numbers, (a, b) -> a.get() - b.get());
        if (numbers.length % 2 == 0) {
            median = (numbers[numbers.length/2 - 1].get() + numbers[numbers.length/2].get()) / 2.0;
        } else {
            median = numbers[numbers.length/2].get();
        }
        System.out.println("Median of the generated numbers: " + median);
    }

    public void standardDeviation(){
        double variance = 0.0;
        for (AtomicInteger number : numbers) {
            variance += Math.pow(number.get() - mean, 2);
        }
        stdDev = Math.sqrt(variance / numbers.length);
        System.out.println("Standard deviation of the generated numbers: " + stdDev);
    }

}

class ThreadMean implements Runnable {
    AtomicVariables a;

    public ThreadMean(AtomicVariables av) {
        this.a = av;
    }

    public void run() {
        a.mean();
    }

}

class ThreadMedian implements Runnable {
    AtomicVariables a;

    public ThreadMedian(AtomicVariables av) {
        this.a = av;
    }

    public void run() {
        a.median();
    }

}

class ThreadStandardDeviation implements Runnable {
    AtomicVariables a;

    public ThreadStandardDeviation(AtomicVariables av) {
        this.a = av;
    }

    public void run() {
        a.standardDeviation();
    }

}

public class Main {
    public static void main(String[] args) {
        AtomicVariables av = new AtomicVariables();
        Thread t1 = new Thread(new ThreadMean(av));
        Thread t2 = new Thread(new ThreadMedian(av));
        Thread t3 = new Thread(new ThreadStandardDeviation(av));
        t1.start();
        t2.start();
        t3.start();
    }
}