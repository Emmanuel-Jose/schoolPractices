import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static JDBCConnection jdbcConnection = new JDBCConnection();

    static synchronized void dbRead() {
        lock.readLock().lock();
        try {
            jdbcConnection.readAllUsers();
        } finally {
            lock.readLock().unlock();
        }
    }

    static synchronized void dbWrite( String name, String lastname ) {
        lock.writeLock().lock();
        try {
            jdbcConnection.insertUser(name, lastname);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {

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
            dbWrite("John6", "Doe6");
        }
    }


    static class Reader implements Runnable {
        @Override
        public void run() {
            dbRead();
        }
    }

}