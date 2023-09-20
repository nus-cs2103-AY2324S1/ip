package anya.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import anya.exception.AnyaException;
import anya.task.Deadline;
import anya.task.Event;
import anya.task.Task;
import anya.task.TaskList;
import anya.task.Todo;

/**
 * The `Storage` class is responsible for managing the storage of task data in the Anya application.
 * It provides functionality to read, write, and convert task data between the application and the storage file.
 */
public class Storage {
    private final String storageFilePath;
    private File source;

    /**
     * Constructs a new `Storage` instance with the specified storage file path.
     *
     * @param path The file path where task data is stored.
     * @throws AnyaException If the provided storage file path is invalid (doesn't end with '.txt').
     */
    public Storage(String path) throws AnyaException {
        this.storageFilePath = path;
        if (!isValidPath(path)) {
            throw new AnyaException("Storage file should end with '.txt'");
        }
        this.source = new File(path);
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     *
     * @param filePath The file path to be checked.
     * @return true if the file path ends with '.txt', indicating it's a valid storage file path.
     */
    private static boolean isValidPath(String filePath) {
        return filePath.endsWith(".txt");
    }

    /**
     * Loads task data from the storage file and returns it as a `TaskList`.
     *
     * @return A `TaskList` containing the loaded tasks.
     * @throws AnyaException If an error occurs while reading or creating the storage file.
     */
    public ArrayList<Task> load() throws AnyaException {
        // Check for directory
        File directory = this.source.getParentFile();
        directory.mkdir();
        try {
            if (source.createNewFile()) {
                return new ArrayList<>();
            }
            return readFile();
        } catch (IOException e) {
            throw new AnyaException("Error writing to file: " + storageFilePath);
        }
    }

    /**
     * Reads task data from the storage file and converts it into a `TaskList`.
     *
     * @return A `TaskList` containing the tasks read from the storage file.
     * @throws FileNotFoundException If the storage file is not found.
     */
    public ArrayList<Task> readFile() throws AnyaException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(this.source);
            while (sc.hasNext()) {
                Task t = convertStringToTask(sc.nextLine());
                tasks.add(t);
            }
            return tasks;
        } catch (UnknownTaskException | FileNotFoundException e) {
            throw new AnyaException(e.getMessage());
        }
    }

    /**
     * Converts a string representation of a task to a `Task` object.
     *
     * @param input The string representation of a task.
     * @return A `Task` object parsed from the input string.
     * @throws UnknownTaskException If an unknown task type is encountered in the input.
     */
    public Task convertStringToTask(String input) throws UnknownTaskException {
        String[] args = input.split("\\|");

        String taskType = args[0].trim();
        boolean isDone = args[1].trim().equals("1");
        String description = args[2].trim();

        switch (taskType) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            String by = args[3].trim();
            return new Deadline(description, by, isDone);
        case "E":
            String from = args[3].trim();
            String to = args[4].trim();
            return new Event(description, from, to, isDone);
        default:
            throw new UnknownTaskException("Unknown task identified: " + taskType);
        }
    }

    /**
     * Saves the tasks from a `TaskList` to the storage file.
     *
     * @param tasks The `TaskList` containing the tasks to be saved.
     */
    public void save(TaskList tasks) throws AnyaException {
        try {
            clearFile();
        } catch (IOException e) {
            throw new AnyaException(e.getMessage());
        }
        writeToFile(tasks);
    }

    private void writeToFile(TaskList tasks) throws AnyaException {
        for (int i = 0; i < tasks.size(); i++) {
            String text = "";
            Task t = tasks.get(i);
            try {
                text += convertTaskToString(t) + System.lineSeparator();
                appendToFile(text);
            } catch (IOException e) {
                throw new AnyaException(e.getMessage());
            }
        }
    }

    /**
     * Converts a `Task` object to its string representation for storage.
     *
     * @param task The `Task` object to be converted.
     * @return A string representation of the task suitable for storage.
     */
    public String convertTaskToString(Task task) {
        return task.formatToSave();
    }

    private void clearFile() throws IOException {
        FileWriter fw = new FileWriter(this.storageFilePath, false);
        fw.write("");
        fw.close();
    }

    private void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(this.storageFilePath, true);
        fw.write(text);
        fw.close();
    }

    /**
     * Signals that an unknown task type was encountered during conversion.
     */
    public static class UnknownTaskException extends Exception {
        public UnknownTaskException(String message) {
            super(message);
        }
    }
}
