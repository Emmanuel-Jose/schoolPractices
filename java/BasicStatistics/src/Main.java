import java.util.Arrays;

class Statistics implements Runnable {
    private Thread t;
    private final String threadName;
    private final double[][] gradesMatrix = {
            { 8.2, 8.5, 7.5 },
            { 8.4, 9.5, 10 },
            { 6.9, 8.4, 7.8 },
            { 7.8, 6.8, 8.6 },
            { 9.7, 8.4, 8.4 },
            { 5.8, 6.8, 8.2 },
            { 6.3, 8.5, 5.6 },
            { 8.8, 8.6, 8.6 },
            { 9.5, 6.8, 6.9 },
            { 10, 5.3, 6.8 },
            { 9.6, 6.8, 7.9 },
            { 9.5, 5.9, 6.8 },
            { 8.3, 5.7, 6.8 },
            { 6.7, 4.8, 10 },
            { 9.6, 7.9, 7.8 },
    };
    double[] average = new double[gradesMatrix.length];
    double[] unitAverage = new double[gradesMatrix[0].length];
    double mean = 0;
    double variance = 0;
    double standardDeviation = 0;


    Statistics(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        switch (threadName) {
            case "Average" -> average();
            case "Unit Average" -> unitAverage();
            case "Variance and Standard Deviation" -> {
                calculateMean();
                varianceStandardDeviation();
            }
        }
    }

    private void average() {
        for (int i = 0; i < gradesMatrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < gradesMatrix[i].length; j++) {
                sum += gradesMatrix[i][j];
            }
            average[i] = sum / gradesMatrix[i].length;
        }
        System.out.println("Students Average: " + Arrays.toString(average));
    }

    private void unitAverage() {
        for (int i = 0; i < gradesMatrix[0].length; i++) {
            double sum = 0;
            for (double[] matrix : gradesMatrix) {
                sum += matrix[i];
            }
            unitAverage[i] = sum / gradesMatrix.length;
        }
        System.out.println("Unit Average: " + Arrays.toString(unitAverage));
    }

    private void calculateMean() {
        double sum = 0;
        for (double[] matrix : gradesMatrix) {
            for (double grade : matrix) {
                sum += grade;
            }
        }
        mean = sum / (gradesMatrix.length * gradesMatrix[0].length);
    }

    private void varianceStandardDeviation() {
        int count = 0;
        for (double[] matrix : gradesMatrix) {
            for (double grade : matrix) {
                variance += Math.pow(grade - mean, 2);
                count++;
            }
        }
        variance /= count;
        standardDeviation = Math.sqrt(variance);
        System.out.println("Variance: " + variance);
        System.out.println("Standard Deviation: " + standardDeviation);
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