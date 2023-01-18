/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author josca
 */
class NumbersCheck {

    boolean flag = false;
    int numberToCheck;
    ThreadLocal<Integer> threadLocalNumber = new ThreadLocal<Integer>();

    public synchronized void generateNumber() {

        if ( flag ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (threadLocalNumber.get() == null) {
            threadLocalNumber.set(0);
        }
        threadLocalNumber.set( ThreadLocalRandom.current().nextInt( 99 ) + 1 );
        this.numberToCheck = threadLocalNumber.get();
        flag = true;
        notify();
    }


    public synchronized void checkNumber() {

        if ( !flag ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // check if a number is prime
        for (int i = 2; i < numberToCheck; i++) {
            if (numberToCheck % i == 0) {
                System.out.println(numberToCheck + " is not a prime number");
                break;
            }
            System.out.println(numberToCheck + " is a prime number, its square is:" + numberToCheck * numberToCheck );
            break;
        }

        flag = false;
        notify();
    }

}

class Thread1 implements Runnable {
    // this thread will send numbers
    NumbersCheck nc;
    int number;

    Thread1(NumbersCheck nc) {
        this.nc = nc;
        new Thread(this, "Question").start();
    }

    public void run() {
        nc.generateNumber();
    }

}


class Thread2 implements Runnable {
    // this thread will check the number
    NumbersCheck nc;

    Thread2(NumbersCheck nc) {
        this.nc = nc;
        new Thread(this, "Question").start();
    }

    public void run() {

        nc.checkNumber();

    }
}


public class TestThread {

    public static void main(String[] args) {
        NumbersCheck nc = new NumbersCheck();
        Thread1 t1 = new Thread1(nc);
        Thread2 t2 = new Thread2(nc);
    }
}