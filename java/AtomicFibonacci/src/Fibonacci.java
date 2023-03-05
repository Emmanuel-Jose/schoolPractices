import java.util.concurrent.atomic.AtomicInteger;

public class Fibonacci {

    AtomicInteger a = new AtomicInteger(0);
    AtomicInteger b = new AtomicInteger(1);
    AtomicInteger c = new AtomicInteger(0);
    AtomicInteger i = new AtomicInteger(0);

    public void fibonacci() {
        while (i.get() < 8) {
            System.out.println(c.get());
            c.set(a.get() + b.get());
            a.set(b.get());
            b.set(c.get());
            i.incrementAndGet();
        }
    }

}
