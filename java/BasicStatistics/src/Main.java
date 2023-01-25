import java.util.Arrays;

class Statistics implements Runnable {
    private Thread t;
    private final String threadName;
    private final double[][] gradesMatrix = {
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
            { 8, 8.5, 9 },
    };

    Statistics(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        switch (threadName) {
            case "Average" -> average();
            case "Unit Average" -> unitAverage();
            case "Variance and Standard Deviation" -> varianceStandardDeviation();
        }
    }

    private void average() {
        double[] average = new double[gradesMatrix.length];
        for (int i = 0; i < gradesMatrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < gradesMatrix[i].length; j++) {
                sum += gradesMatrix[i][j];
            }
            average[i] = sum / gradesMatrix[i].length;
        }
        System.out.println("Average: " + Arrays.toString(average));
    }

    private void unitAverage() {
        double[] unitAverage = new double[gradesMatrix[0].length];
        for (int i = 0; i < gradesMatrix[0].length; i++) {
            double sum = 0;
            for (double[] matrix : gradesMatrix) {
                sum += matrix[i];
            }
            unitAverage[i] = sum / gradesMatrix.length;
        }
        System.out.println("Unit Average: " + Arrays.toString(unitAverage));
    }

    private void varianceStandardDeviation() {
        double[] variance = new double[gradesMatrix[0].length];
        double[] standardDeviation = new double[gradesMatrix[0].length];
        for (int i = 0; i < gradesMatrix[0].length; i++) {
            double sum = 0;
            for (double[] matrix : gradesMatrix) {
                sum += matrix[i];
            }
            double unitAverage = sum / gradesMatrix.length;
            double sumOfSquares = 0;
            for (double[] matrix : gradesMatrix) {
                sumOfSquares += Math.pow(matrix[i] - unitAverage, 2);
            }
            variance[i] = sumOfSquares / gradesMatrix.length;
            standardDeviation[i] = Math.sqrt(variance[i]);
        }
        System.out.println("Variance: " + Arrays.toString(variance));
        System.out.println("Standard Deviation: " + Arrays.toString(standardDeviation));
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
        Statistics averageThread = new Statistics("Average");
        Statistics unitAverageThread = new Statistics("Unit Average");
        Statistics varianceStandardDeviationThread = new Statistics("Variance and Standard Deviation");

        averageThread.start();
        unitAverageThread.start();
        varianceStandardDeviationThread.start();
    }
}