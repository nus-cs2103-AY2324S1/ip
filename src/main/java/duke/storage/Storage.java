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

    /** The complete file path of the storage file, including directory and file name. */
    private final String completeFilePath;

    /** The directory path where the storage file is located. */
    private final String directoryPath;

    /** The file object representing the storage file. */
    private final File saveFile;

    /**
     * Constructs a Storage object with the given directory name and file name.
     *
     * @param directoryName The name of the directory where the file is stored.
     * @param fileName The name of the file for storage.
     * @throws DukeException If there's an error while initializing the storage.
     */
    public Storage(String directoryName, String fileName) throws DukeException {
        this.directoryPath = "./" + directoryName;
        this.completeFilePath = this.directoryPath + "/" + fileName;
        this.saveFile = new File(this.completeFilePath);

        createFileIfNotExists();
    }

    /**
     * Checks if the duke.txt file exists, and if not, creates an empty one.
     *
     * @throws DukeException If there's an error creating the file or directory.
     */
    private void createFileIfNotExists() throws DukeException {
        try {
            File directory = new File(this.directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }

            if (!this.saveFile.exists()) {
                this.saveFile.createNewFile();
            }
        } catch (IOException e) {
            // Handle the exception, e.g., log an error message
            throw new DukeException("Cannot create new save file!");
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
            File file = new File(this.completeFilePath);
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
            File file = new File(this.completeFilePath);
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
