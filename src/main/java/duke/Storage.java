package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeStorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a storage for the Duke chat-bot that stores the tasks in a file.
 * This serves to allow the tasks to be saved and loaded on the hard drive when Duke starts and exits.
 */

public class Storage {
    private final String filePath;

    /**
     * Creates a Storage object.
     *
     * @param filePath The path to the file to store the tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the file that stores the tasks if it does not exist.
     *
     * @param file The file to be created.
     * @throws DukeStorageException If the file cannot be created, or some other IOException is thrown.
     */
    private void createFile(File file) throws DukeStorageException {
        try {
            boolean isCreated = file.getParentFile().mkdirs() && file.createNewFile();
            if (!isCreated && !file.exists()) {
                throw new DukeStorageException("Something went wrong with creating the store of tasks.");
            }
        } catch (IOException e) {
            throw new DukeStorageException("Something went wrong with creating the store of tasks.");
        }
    }

    /**
     * Loads the tasks from the file provided by the filepath when Duke first starts.
     *
     * @return The list of tasks.
     * @throws DukeStorageException If the file cannot be created, or some other IOException is thrown.
     */
    public List<Task> loadData() throws DukeStorageException {
        File file = new File(filePath);
        List<Task> tasks = new ArrayList<>();

        try {
            Scanner loader = new Scanner(file);
            while (loader.hasNextLine()) {
                String[] fields = loader.nextLine().split(";");
                String description = fields[2];
                boolean isDone = fields[1].equals("X");

                switch (fields[0]) {
                case "T":
                    tasks.add(new Todo(description, isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(description, isDone, LocalDateTime.parse(fields[3])));
                    break;
                case "E":
                    tasks.add(new Event(description, isDone, LocalDateTime.parse(fields[3]),
                            LocalDateTime.parse(fields[4])));
                    break;
                default:
                    // Do nothing if the task is not recognised
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            createFile(file);
        }
        return tasks;
    }

    /**
     * Saves the tasks to the file provided by the filepath after Duke exits.
     *
     * @param tasks The list of tasks.
     * @throws DukeStorageException If the file cannot be created, or some other IOException is thrown.
     */
    public void saveData(TaskList tasks) throws DukeStorageException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(tasks.stringifyTasks());
        } catch (IOException e) {
            throw new DukeStorageException("Something went wrong with saving the tasks");
        }
    }
}
