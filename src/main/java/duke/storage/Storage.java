package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a class for managing data storage and retrieval.
 */
public class Storage {
    private static final String LINE = "___________________________________\n";
    private final String filepath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The path to the file where data will be stored.
     */
    public Storage(String filepath) {
        assert filepath != null;
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws DukeException If there is an issue loading tasks from the file.
     */
    public TaskList load() throws DukeException {
        TaskList lst = new TaskList(new ArrayList<>());
        try (Scanner fileScanner = new Scanner(new File(this.filepath))) {
            while (fileScanner.hasNextLine()) {
                String taskString = fileScanner.nextLine();
                String[] arr = taskString.split("\\|");
                lst.readListFromFile(arr);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Looks like this is your first time!\n"
                    + "Let's start with a new list!\n" + LINE);
        }
        return lst;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param list The ArrayList of tasks to be saved.
     */
    public void saveDataToFile(ArrayList<Task> list) throws DukeException {
        File folder = new File("./data/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try (PrintWriter writer = new PrintWriter(this.filepath)) {
            for (Task task : list) {
                writer.println(task.toStringFile());
            }

        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
