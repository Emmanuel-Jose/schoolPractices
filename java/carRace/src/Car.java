import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class Car extends Observable implements Runnable {

    private String name;
    ThreadLocal<Integer> threadLocalNumber = new ThreadLocal<Integer>();

    public Car( String name ){
        this.name = name;
    }

    @Override
    public void run() {
        int length = 0;
        int randomNumber;

        try {
            randomNumber = GetRandomNumber();
            System.out.println("Car " + name + " number: " + randomNumber + " length: " + length);
            length += randomNumber;

            this.setChanged();
            this.notifyObservers(length);
            this.clearChanged();

            Thread.sleep(1000);

        } catch (InterruptedException ex){
            System.out.println("Thread interrupted");
        }


    }

    public int GetRandomNumber(){
        if ( threadLocalNumber.get() == null ) {
            threadLocalNumber.set(0);
        }
        threadLocalNumber.set(ThreadLocalRandom.current().nextInt(9 + 1));
        return threadLocalNumber.get();
    }

    public String getName(){
        return name;
    }

}
