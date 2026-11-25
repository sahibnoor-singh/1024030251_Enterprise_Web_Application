// Q2 - Read a log file and save errors and warnings
import java.io.*;

public class Q2_LogAnalyzer {
    public static void main(String[] args) {
        int totalEntries = 0;
        int filteredEntries = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("application.log"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("filtered_logs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalEntries++;
                if (line.contains("ERROR") || line.contains("WARNING")) {
                    writer.write(line);
                    writer.newLine();
                    filteredEntries++;
                }
            }
            System.out.println("Total log entries: " + totalEntries);
            System.out.println("Errors and warnings: " + filteredEntries);
            System.out.println("Filtered entries saved to filtered_logs.txt");
        } catch (IOException e) {
            System.out.println("Error processing log file: " + e.getMessage());
        }
    }
}
