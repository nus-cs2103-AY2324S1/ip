package storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import parser.DataParser;
import tasks.Task;

/**
 * The DataReader class is responsible for reading task data from a storage file and converting it into Task objects.
 */
public class DataReader {

    private static String path;

    /**
     * Constructs a DataReader instance with the specified file path.
     *
     * @param path The file path of the storage file to be read.
     */
    public DataReader(String path) {
        DataReader.path = path;
    }

    /**
     * Reads tasks from the storage file and converts them into an ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects representing the tasks read from the storage file.
     */
    public static ArrayList<Task> readTasksFromFile() {
        ArrayList<Task> tasksList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DataReader.path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = DataParser.parseLineToTask(line);
                if (task != null) {
                    tasksList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file or the file doesn't exist.");
            System.out.println("Please try again after ensuring the correctness of the file.");
            System.exit(1);
        }
        return tasksList;
    }
}
