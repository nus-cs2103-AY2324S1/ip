package dukduk;

import java.io.*;
import java.util.ArrayList;

/**
 * The class responsible for loading and saving tasks from/to a file.
 */
public class Storage {

    private static final String FILE_PATH = "src/main/java/data/duke.txt";

    /**
     * Creates the data storage directory and file if they do not exist.
     * If the directory or file creation fails, appropriate error messages are displayed.
     */
    public static void createDataLocation() {
        File dir = new File("src/main/java/data");

        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created: " + dir.getAbsolutePath());
            } else {
                System.err.println("Failed to create directory: "
                        + dir.getAbsolutePath());
                return;
            }
        }

        File db = new File(FILE_PATH);
        if (!db.exists()) {
            try {
                if (db.createNewFile()) {
                    System.out.println("File created: "
                            + db.getAbsolutePath());
                } else {
                    System.err.println("Failed to create file: "
                            + db.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("Error when creating the data storage!");
            }
        }
    }

    /**
     * Loads tasks from a specified file.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("No existing tasks file found. Starting with an empty task list.");
                return tasks;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                processTasks(line, tasks);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Processes a single line from the tasks file and adds the corresponding Task to the tasks list.
     * If the line contains valid task data, a Task object is created and added to the list.
     * If there is an error parsing the task data, an error message is displayed.
     *
     * @param line   The line of task data to be processed.
     * @param tasks  The list of tasks to which the processed task will be added.
     */
    private static void processTasks(String line, ArrayList<Task> tasks) {
        try {
            Task task = Task.createTaskFromDataString(line);
            tasks.add(task);
            String[] parts = line.split(" \\| ");
            if (parts.length >= 2 && parts[1].equals("1")) {
                task.markAsDone();
            }
        } catch (DukdukException e) {
            System.out.println("Error parsing task data: " + e.getMessage());
        }
    }

    /**
     * Saves tasks to a specified file.
     *
     * @param tasks    An ArrayList of tasks to be saved to the file.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toDataString());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
