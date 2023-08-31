package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles storage operations including loading tasks from a file and saving tasks to a file.
 * <p>
 * This class provides methods to interact with the storage file where tasks are saved.
 * It ensures that the necessary directory and file exist and provides utility functions
 * to read and write tasks from and to the storage file.
 * </p>
 */
public class Storage {
    private static final String FILE_PATH = "./data/duke.txt";
    private final Ui ui;

    /**
     * Constructs a new {@code Storage} object with a reference to the UI.
     *
     * @param ui The UI instance for displaying storage-related messages.
     */
    public Storage(Ui ui) {
        this.ui = ui;
    }

    /**
     * Ensures the storage directory exists.
     * <p>
     * If the directory does not exist, it is created.
     * </p>
     */

    private void ensureDirectoryExists() {
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Saves the provided list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */

    public void saveTasks(List<Task> tasks) {
        try {
            ensureDirectoryExists();
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            ui.printIndented("Error saving tasks to file.");
        }
    }

    /**
     * Loads tasks from the storage file into a list.
     *
     * @return A list of tasks loaded from the storage file.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            ensureDirectoryExists();
            File f = new File(FILE_PATH);
            if (!f.exists()) return tasks;
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String taskData = s.nextLine();
                Task task = parseFileTask(taskData);
                tasks.add(task);
            }
            s.close();
        } catch (IOException e) {
            ui.printIndented("Error loading tasks from file.");
        } catch (DukeException e) {
            ui.printIndented("Data file is corrupted: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a task represented as a string from the storage file.
     *
     * @param taskData The task data as a string.
     * @return A {@link Task} object based on the provided task data.
     * @throws DukeException If the task type is unknown or there's an error in parsing.
     */
    private Task parseFileTask(String taskData) throws DukeException {
        String[] parts = taskData.split(" \\| ");
        switch (parts[0]) {
            case "T":
                ToDo todo = new ToDo(parts[2]);
                if (parts[1].equals("1")) todo.markAsDone();
                return todo;
            case "D":
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) deadline.markAsDone();
                return deadline;
            case "E":
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) event.markAsDone();
                return event;
            default:
                throw new DukeException("Unknown task type: " + parts[0]);
        }
    }
}
