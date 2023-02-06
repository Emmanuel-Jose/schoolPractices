import java.util.Random;

class ThreadDemo extends Thread {
    static char[][] board = new char[3][3];
    static int turn = 0;
    static Random rand = new Random();
    static boolean gameOver = false;
    private Thread t;
    String threadName;

    ThreadDemo(String name) {
        threadName = name;
        System.out.println("Creating " + name);
    }

    public static void printBoard() {
        System.out.println('\n');
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\u0000') {
                    System.out.print("*");
                } else {
                    System.out.print(board[i][j]);
                }
                if (j < 2) {
                    System.out.print("|");
                }
            }
            if (i < 2) {
                System.out.println("\n-----");
            }
        }
        System.out.println();
    }

    public static boolean isWinner( char player ) {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    public static boolean isBoardFull() {
        for (char[] row : board) {
            for (char c : row) {
                if (c == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void play(int player){
        int row, col;
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (board[row][col] != '\u0000');
        if (player == 1) {
            board[row][col] = 'X';
            if ( isWinner('X') ) {
                System.out.println('\n');
                System.out.println("Player 1 wins!");
                gameOver = true;
                printBoard();
            } else if (isBoardFull()) {
                System.out.println("Draw!");
                gameOver = true;
                printBoard();
            }
        } else {
            board[row][col] = 'O';
            if ( isWinner('O') ) {
                System.out.println('\n');
                System.out.println("Player 2 wins!");
                gameOver = true;
                printBoard();
            } else if (isBoardFull()) {
                System.out.println('\n');
                System.out.println("Draw!");
                gameOver = true;
                printBoard();
            }
        }
    }

    public void run() {
        synchronized (this) {
//            System.out.println("Running " + threadName);
            while (!gameOver) {
                if (turn == 0) {
                    play(1);
                    turn = 1;
                } else {
                    play(2);
                    turn = 0;
                }
            }
            System.out.println("Thread " + threadName + " exiting.");
        }
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

}

public class Main {
    public static void main(String[] args) {
        ThreadDemo T1 = new ThreadDemo("Thread-1");
        ThreadDemo T2 = new ThreadDemo("Thread-2");
        T1.start();
        T2.start();

        // wait for threads to end
        try {
            T1.join();
            T2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
    }
}