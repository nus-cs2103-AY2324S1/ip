package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents a storage handler for managing reading from and writing to a file.
 * This class handles IO operations with the file to store and retrieve user data.
 */
public class Storage {
    /** The instance of the user data file. */
    protected File file;

    /**
     * Initializes a new Storage with a specified file path.
     * If the directory does not exist, it will be created.
     *
     * @param filePath The directory path where the user data file is to be stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path should not be null or empty!";

        Path path = Paths.get(filePath);
        this.file = path.toFile();
        if (!file.exists()) {
            try {
                Files.createFile(path);
                System.out.println("File created: " + path);
                TaskMaster.setPastData(false);
            } catch (IOException e) {
                System.err.println("Failed to create file: " + e.getMessage());
            }
        } else {
            System.out.println("File already exists: " + path);
            TaskMaster.setPastData(true);
        }
    }

    /**
     * Retrieves the past saved tasks from the user data file.
     *
     * @return An ArrayList of tasks retrieved from the file.
     */
    public ArrayList<Task> getPastData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<Task> tasks = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(stringToTask(line));
            }
            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Stores the updated list of tasks to the user data file.
     *
     * @param tasks The list of tasks to be stored.
     */
    public void storeNewData(ArrayList<Task> tasks) {
        clearFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (Task task : tasks) {
                writer.write(taskToString(task));
                writer.newLine();
            }
            System.out.println("Successfully store updated tasks");
        } catch (IOException e) {
            System.out.println("Failed to store tasks to file.\n" + e.getMessage());
        }
    }

    /**
     * Converts a string representation of a task from the file into a Task object.
     *
     * @param line The string representation of the task.
     * @return The corresponding Task object.
     */
    private Task stringToTask(String line) {
        String[] split = line.split(" \\| ");
        switch (split[0]) {
            case "[T]": {
                return Todos.toTask(split);
            }
            case "[D]": {
                return Deadlines.toTask(split);
            }
            case "[E]": {
                return Events.toTask(split);
            }
            default: {
                System.out.println("File contains illegal task format!");
                return null;
            }
        }
    }

    private String taskToString(Task task) {
        return task.getSavingFormat();
    }

    private void clearFile() {
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            // clear the file
        } catch (IOException e) {
            System.err.println("Error clearing the file: " + e.getMessage());
        }
    }
}
