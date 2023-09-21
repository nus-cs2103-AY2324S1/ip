package storage;

import java.io.*;
import java.util.ArrayList;

import parser.DataParser;
import tasks.Task;
import parser.TaskParser;

public class DataReader {

    private static final String FILE_PATH = "src" + File.separator + "data" + File.separator + "tasks.txt";

    /**
     * Reads tasks from the storage file and converts them into an ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects representing the tasks read from the storage file.
     */
    public static ArrayList<Task> readTasksFromFile() {
        ArrayList<Task> tasksList = new ArrayList<>();

        // Read the file line by line
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                System.out.println("The file does not exist. Creating a new one.");
                if (file.createNewFile()) {
                    System.out.println("File created: " + FILE_PATH);
                } else {
                    System.out.println("Failed to create the file.");
                }
            } else {
                // File exists, read its content
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = DataParser.parseLineToTask(line);
                    if (task != null) {
                        tasksList.add(task);
                    }
                }
                reader.close();
                return tasksList;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return tasksList;
    }
}
