package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents the storage component of the Duke chatbot.
 * Handles loading and saving of tasks to a file.
 */
public class Storage {

    /** The file path of the storage */
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The file path of the file to be used for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createFileIfNotExists();
    }

    /**
     * Checks if the duke.txt file exists, and if not, creates an empty one.
     */
    private void createFileIfNotExists() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            // Handle the exception, e.g., log an error message
            System.err.println("Error creating the file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws DukeException If an error occurs while loading the tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                Task task = Task.createTaskFromFormattedString(line);
                if (task != null && !isDuplicateTask(tasks, task)) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file.");
        }
        return tasks;
    }

    /**
     * Checks if a given task is a duplicate of any task in the list.
     *
     * @param tasks The list of tasks to check against.
     * @param task  The task to check for duplication.
     * @return true if the task is a duplicate, false otherwise.
     */
    public boolean isDuplicateTask(ArrayList<Task> tasks, Task task) {
        for (Task t : tasks) {
            if (t.isDuplicate(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The ArrayList of tasks to be saved.
     * @throws DukeException If an error occurs while saving the tasks.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.toFormattedString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file.");
        }
    }
}
