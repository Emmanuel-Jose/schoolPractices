import java.util.concurrent.ThreadLocalRandom;

public class FirstCar implements Runnable {
    int speed;
    ThreadLocal<Integer> threadLocalSpeed = new ThreadLocal<Integer>();

    //constructor
    FirstCar(){
        new Thread(this, "Car1").start();
    }

    public void generateSpeed(){
        threadLocalSpeed.set(ThreadLocalRandom.current().nextInt(99) + 1);
        this.setSpeed(threadLocalSpeed.get());
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        generateSpeed();
        System.out.println("First car speed: " + this.speed);
    }
}
