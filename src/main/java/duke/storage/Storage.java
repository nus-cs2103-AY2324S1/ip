package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.commands.Parser;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a storage for tasks
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file path
     *
     * @return An ArrayList of tasks
     * @throws DukeException If the file path is invalid
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                task = Parser.parseFileString(scanner.nextLine());
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Data file not found, starting with an empty task list.");
        }
        return tasks;
    }

    /**
     * Saves tasks to the file path
     *
     * @param tasks The task list to be saved
     * @throws DukeException If the file path is invalid
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                writer.println(Parser.readTaskToFile(tasks.getTask(i)));
            }
        } catch (IOException | DukeException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
