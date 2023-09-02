package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Task;

/**
 * Represents a storage that stores the tasks in a file.
 */
public class Storage {
    private String path;
    private File file;

    /**
     * Constructs a Storage object.
     *
     * @param path The path of the file to store the tasks.
     */
    public Storage(String path) {
        this.path = path;
        this.file = new File(this.path);
    }

    /**
     * Creates a file to store the tasks if the file does not exist.
     *
     * @throws DukeException If the file cannot be created.
     */
    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter filewriter = new FileWriter(this.path);
            for (Task task : tasks) {
                filewriter.write(task.toSaveString() + "\n");
            }
            filewriter.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    /**
     * Loads the tasks from the file.
     *
     * @return An ArrayList of tasks.
     * @throws DukeException If the file cannot be loaded.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = new Parser().parseTask(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
        return tasks;
    }
}
