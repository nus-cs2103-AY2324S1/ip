package duke.utils;

import duke.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The `Storage` class handles reading and writing tasks to a data file.
 */
public class Storage {
    private final String DATA_FILE_PATH = "data.txt";

    /**
     * Saves a list of tasks to a data file.
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
     * Loads tasks from a data file into a list.
     * @param toDoList The list where loaded tasks will be added.
     */
    public void loadTasksFromFile(List<Task> toDoList) {
        try {
            File file = new File(DATA_FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String taskData = scanner.nextLine();
                    Task task = Task.createTaskFromData(taskData);
                    if (task != null) {
                        toDoList.add(task);
                    }
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}
