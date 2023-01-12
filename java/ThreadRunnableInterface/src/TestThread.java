/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jose bayona
 */
class RunnableDemo implements Runnable {

    private Thread t;
    private final String threadName;
     private final char [][] matrix ={
            { 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z' },
            { 'p', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'q' },
            { 'h', 'h', 'e', 'l', 'l', 'o', 'u', 'i', 'o', 'p' },
            { 'e', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'o', 'z' },
            { 'l', 'e', 'r', 'h', 'e', 'l', 'l', 'o', 'l', 'q' },
            { 'l', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z' },
            { 'o', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'e', 'q' },
            { 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'h', 'z' },
            { 'w', 'e', 'o', 'l', 'l', 'e', 'h', 'o', 'p', 'q' },
            { 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z' }
    };

    RunnableDemo(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {

        System.out.println("Running " + threadName);

        switch (threadName) {
            case "left to right" -> {

                for (char[] chars : matrix) {

                    String joinedArray = new String(chars);

                    if (joinedArray.contains("hello")) {

                        System.out.println("Found hello in " + threadName + " *******");

                    }

                }
            }
            case "right to left" -> {

                for (char[] chars : matrix) {

                    String joinedArray = new String(chars);

                    if (joinedArray.contains("olleh")) {

                        System.out.println("Found hello in " + threadName + " *******");

                    }

                }
            }
            case "top to bottom" -> {

                for (int i = 0; i < matrix[0].length; i++) {

                    StringBuilder txt = new StringBuilder();

                    for (char[] chars : matrix) {

                        txt.append(chars[i]);

                    }

                    if (txt.toString().contains("hello")) {

                        System.out.println("Found hello in " + threadName + " *******");

                    }

                }
            }
            case "bottom to top" -> {

                for (int i = 0; i < matrix[0].length; i++) {

                    StringBuilder txt = new StringBuilder();

                    for (char[] chars : matrix) {

                        txt.append(chars[i]);

                    }

                    if (txt.toString().contains("olleh")) {

                        System.out.println("Found hello in " + threadName + " *******");

                    }

                }
            }
        }

        System.out.println( "Thread " + threadName + " exiting." );

    }

    public void start() {
        System.out.println("Starting " + threadName);

        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

public class TestThread {

    public static void main(String[] args) {


        RunnableDemo leftToRightThread = new RunnableDemo( "left to right" );
        RunnableDemo rightToLeftThread = new RunnableDemo( "right to left" );
        RunnableDemo topToBottom       = new RunnableDemo( "top to bottom" );
        RunnableDemo bottomToTop       = new RunnableDemo( "bottom to top" );

        leftToRightThread.start();
        rightToLeftThread.start();
        topToBottom.start();
        bottomToTop.start();

    }
    
}
