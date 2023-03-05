import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class infoLog {

    static String filePath = "cisco_log.csv";


    private static List readColumn( int columnIndex ) throws IOException {
        List<String> columnValues = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                columnValues.add(values[columnIndex]);
            }
        }
        columnValues.remove(0);
//        System.out.println(columnValues);
        return columnValues;
    }

    private static void getStatistics( String threadName, List values ) throws IOException {
        repetitions( threadName, values);
    }

    private static void repetitions(String name, List columnValues) {
        // Count frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (Object word : columnValues) {
            frequencyMap.put((String) word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Find word that repeats the most
        int maxFrequency = 0;
        String mostFrequentWord = "";
        for (String word : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(word);
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                mostFrequentWord = word;
            }
        }
        System.out.println("the most common  "+ name + ": "  + mostFrequentWord);

        // Find word that repeats the least
        int minFrequency = Integer.MAX_VALUE;
        String leastFrequentWord = "";
        for (String word : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(word);
            if (frequency < minFrequency) {
                minFrequency = frequency;
                leastFrequentWord = word;
            }
        }
        System.out.println("the least common "+ name + ": "  + leastFrequentWord);

    }

    public static void main(String[] args) throws IOException {

        int sourcePortIndex = 0;
        int destinationPortIndex = 1;
        int protocolNameIndex = 2;
        int categoryDescription = 6;

        InfoThreads sourcePortThread = new InfoThreads("Source Port Thread", sourcePortIndex);
        InfoThreads destinationPortThread = new InfoThreads("Destination Port Thread", destinationPortIndex);
        InfoThreads protocolNameThread = new InfoThreads("Protocol Name Thread", protocolNameIndex);
        InfoThreads categoryDescriptionThread = new InfoThreads("Event Category Thread", categoryDescription);

        sourcePortThread.start();
        destinationPortThread.start();
        protocolNameThread.start();
        categoryDescriptionThread.start();

    }

    private static class InfoThreads implements Runnable {
        private Thread t;
        private final String threadName;
        private final int columnIndex;

        InfoThreads(String name, int column) {
            threadName = name;
            columnIndex = column;
        }

        public void run() {
            try {
                List columnValues = readColumn(columnIndex);
                getStatistics( threadName, columnValues );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void start() {
            if (t == null) {
                t = new Thread(this, threadName);
                t.start();
            }
        }
    }

}