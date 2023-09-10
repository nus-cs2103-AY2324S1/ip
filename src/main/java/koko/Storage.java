package koko;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the storage of tasks, including saving and loading from a file.
 */
public class Storage {

    private final String filePath;

    /**
     * Initializes a new Storage object with the specified file path.
     * @param filePath The path to the file for saving and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to a file.
     * @param taskListToSave The list of tasks to save.
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasksToFile(TaskList taskListToSave) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(taskListToSave.toStringForFile());
        fw.close();

    }

    /**
     * Loads tasks from a file into a TaskList object.
     * @return A new TaskList object containing the loaded tasks.
     * @throws FileNotFoundException If the file is not found.
     * @throws DukeException If an error occurs while parsing the file.
     */
    public TaskList loadTasksFromFile() throws FileNotFoundException, DukeException {
        File file = new File(filePath);

        Scanner scanner = new Scanner(file);

        ArrayList<Task> result = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            try {
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    result.add(task);
                }
            } catch (DukeException dukeException) {
                throw dukeException;
            } catch (Exception e) {
                throw new DukeException("Error while parsing data file -- possibly corrupt?"
                        + "File will be overwritten if you proceed.");
            }
        }
        return new TaskList(result);
    }
}
