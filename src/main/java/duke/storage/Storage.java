package duke.storage;

import duke.Duke;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.data.task.Task;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The Storage class is responsible for loading and saving tasks from/to a data file for the ChatterChicken task manager application.
 * It interacts with the data file to read and write task information, ensuring data persistence.
 */
public class Storage {

    Parser parser;

    /**
     * Constructs a new Storage object with the specified parser.
     *
     * @param parser The parser used to interpret and convert data from the data file.
     */
    public Storage(Parser parser) {
        this.parser = parser;
        assert parser != null : "Parser should not be null";
    }

    /**
     * Loads tasks from the data file and returns them as an ArrayList of tasks.
     * If the data file doesn't exist, a new file is created.
     *
     * @return An ArrayList containing the loaded tasks, or null in case of errors.
     */
    public ArrayList<Task> loadTasksFromFile() {
        try {
            File dataFile = Paths.get(Duke.PATH).toAbsolutePath().toFile();
            ArrayList<Task> taskList = new ArrayList<>();
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(dataFile));
                String currLine = reader.readLine();
                while (currLine != null) {
                    Task task = parser.parseTaskFromFile(currLine);
                    taskList.add(task);
                    currLine = reader.readLine();
                }
                reader.close();
            }
            assert taskList != null : "TaskList should not be null";
            return taskList;
        } catch (IOException e) {
            System.err.println("An error occurred while loading tasks from file: " + e.getMessage());
        } catch (DukeException e) {
            System.err.println("An error occurred while adding tasks: " + e.getMessage());
        }
        System.out.println("HERE");
        return null;
    }

    /**
     * Saves tasks from the provided TaskList to the data file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     */
    public void saveTasksToFile(TaskList taskList) {
        try {
            File dataFile = Paths.get(Duke.PATH).toAbsolutePath().toFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
            for (Task task : taskList) {
                String taskDescription = task.getTaskForSaving() + "\n";
                writer.append(taskDescription);
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

}
