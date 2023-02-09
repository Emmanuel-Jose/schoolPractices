import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static JDBCConnection jdbcConnection = new JDBCConnection();

    static synchronized void dbRead() {
        jdbcConnection.readAllUsers();
    }

    static synchronized void dbWrite( String name, String lastname ) {
        jdbcConnection.insertUser(name, lastname);
    }

    public static synchronized void main(String[] args) {

        Thread writer = new Thread( new Writer() );
        Thread reader = new Thread( new Reader() );
        Thread reader2 = new Thread( new Reader() );

        writer.setName("Writer");
        reader.setName("Reader");
        reader2.setName("Reader2");

        writer.start();
        reader.start();
        reader2.start();

    }

    static class Writer implements Runnable {

        @Override
        public void run() {
            lock.writeLock().lock();
            try {
                dbWrite("mario", "Alvarado");
            } finally {
                lock.writeLock().unlock();
            }
        }
    }


    static class Reader implements Runnable {
        @Override
        public void run() {
            lock.readLock().lock();
            try {
                dbRead();
            } finally {
                lock.readLock().unlock();
            }
        }
    }

}