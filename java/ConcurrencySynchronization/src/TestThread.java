/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chrio
 */
class PrintDemo {
   // this is the mutex, this is the shared resources
   public void printCount() {
      
      try {
         
         for(int i = 5; i > 0; i--) {
            System.out.println("Counter   ---   "  + i );
         }
      } catch (Exception e) {
         System.out.println("Thread  interrupted.");
      }
   }
}

class ThreadDemo extends Thread {
   private Thread t;
   private String threadName;
   PrintDemo  PD;

   ThreadDemo(String name,  PrintDemo pd) {
      threadName = name;
      PD = pd;
   }
   
   public void run() {
      // java library that is in charge of synchronization, it does the synchronization automatically
      synchronized(PD) {
         PD.printCount();
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }

   public void start () {
      System.out.println("Starting " +  threadName );
      
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}

public class TestThread {

   public static void main(String args[]) {
      PrintDemo PD = new PrintDemo();

      ThreadDemo T1 = new ThreadDemo("Thread - 1 ", PD);
      ThreadDemo T2 = new ThreadDemo("Thread - 2 ", PD);

      T1.start();
      T2.start();

      // wait for threads to end
      try {
         // destory the threads, this is optional, when a thread stop it is destroyed automatically
         T1.join();
         T2.join();
      } catch (Exception e) {
         System.out.println("Interrupted");
      }
   }
}
