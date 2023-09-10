package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Task;


/**
 * The `Storage` class handles reading and writing tasks to a data file.
 */
public class Storage {
    private static final String DATA_FILE_PATH = "data.txt";

    /**
     * Saves a list of tasks to a data file.
     *
     * @param toDoList The list of tasks to be saved.
     * @throws DukeException If an error occurs during file writing.
     */
    public void saveTasksToFile(List<Task> toDoList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(DATA_FILE_PATH);
            for (Task task : toDoList) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } catch (NullPointerException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Loads a list of tasks from a data file.
     *
     * @return A List of Task objects loaded from the data file.
     * @throws DukeException If there is an error reading the data file or creating tasks from the data.
     */
    public List<Task> loadTasksFromFile() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(DATA_FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String taskData = scanner.nextLine();
                    Task task = Task.createTaskFromData(taskData);
                    tasks.add(task);
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }
}
