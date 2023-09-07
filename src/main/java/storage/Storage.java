package storage;

import exception.DukeException;
import task.Task;
import ui.Ui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a storage.Storage object with the specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return An ArrayList of task.Task objects loaded from the file.
     * @throws DukeException If there is an error reading the file or parsing tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Task.fromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new DukeException(Ui.loadingError(e.getMessage()));
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The ArrayList of task.Task objects to be saved to the file.
     * @throws DukeException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false); // Set append mode to false
            for (Task task : tasks) {
                fileWriter.write(task.toFileString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(Ui.saveError(e.getMessage()));
        }
    }
}
