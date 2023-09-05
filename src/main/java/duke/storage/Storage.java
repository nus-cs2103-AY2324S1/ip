package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import duke.task.Task;

/**
 * Handles loading and saving tasks.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as a list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> loadTasks() {
        // Load tasks from the file and return them
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        File folder = file.getParentFile();

        // Create the parent folder if it doesn't exist
        if (!folder.exists() && !folder.mkdirs()) {
            System.err.println("Unable to create directory: " + folder.getAbsolutePath());
            return tasks; // Return an empty list
        }

        try {
            if (!file.exists() && !file.createNewFile()) {
                System.err.println("Unable to create file: " + filePath);
                return tasks; // Return an empty list
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String formattedTask = scanner.nextLine();

                if (formattedTask.isEmpty()) {
                    continue; // Skip empty lines
                }

                try {
                    Task task = Task.fromFileString(formattedTask);
                    tasks.add(task);
                } catch (NumberFormatException e) {
                    // Handle corrupted data - logging the issue
                    Logger logger = Logger.getLogger(Storage.class.getName());
                    logger.log(Level.SEVERE, "Corrupted data: " + formattedTask, e);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid data: " + formattedTask);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("An error occurred while handling file operations: " + e.getMessage());
        } catch (Exception e) {
            Logger logger = Logger.getLogger(Storage.class.getName());
            logger.log(Level.SEVERE, "An error occurred while loading tasks", e);
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        // Save tasks to the file
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
