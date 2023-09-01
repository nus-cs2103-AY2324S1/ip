package deterministicparrot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {

    private static final String DATA_FILE_PATH = "./data/data.txt";

    public Storage() {
    }

    /**
     * Loads data from the file and returns the raw string content.
     */
    public String load() throws FileNotFoundException {
        File file = new File(DATA_FILE_PATH);
        StringBuilder rawData = new StringBuilder();

        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNext()) {
                    rawData.append(fileScanner.nextLine()).append("\n");
                }
            }
        } else {
            createEmptyDataFile();
        }
        return rawData.toString();
    }

    /**
     * Writes the provided raw string content to the file.
     */
    public void save(String rawData) throws FileNotFoundException {
        try (PrintWriter fileWriter = new PrintWriter(DATA_FILE_PATH)) {
            fileWriter.print(rawData); // Using print to avoid adding an additional newline
        }
    }

    /**
     * Creates the necessary directories and an empty data file if they don't exist.
     */
    private void createEmptyDataFile() throws FileNotFoundException {
        File file = new File(DATA_FILE_PATH);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try (PrintWriter fileWriter = new PrintWriter(DATA_FILE_PATH)) {
            // Create an empty file
        }
    }
}
