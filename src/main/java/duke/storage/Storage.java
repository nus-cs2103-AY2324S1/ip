package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Handles the loading and saving of task data for chatbot.
 * Manages reading from and writing to a specified file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Edits and saves task data to the specified file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     * @throws DukeException If there is an issue saving the data.
     */
    public void editData(TaskList taskList) throws DukeException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
            for (Task t : taskList) {
                writer.write(t.toSave() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!! Unable to save data safely.");
        }
    }

    /**
     * Loads task data from the specified file and returns a TaskList.
     *
     * @return The TaskList containing the loaded tasks.
     * @throws DukeException If there is an issue loading the data.
     */
    public TaskList loadData() throws DukeException {
        TaskList loadedTask = new TaskList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currLine;
            while ((currLine = reader.readLine()) != null && !currLine.isEmpty()) {
                String[] parseCurr = currLine.split(" ", 2);
                String completed = parseCurr[0];
                String command = parseCurr[1];
                Command c = Parser.parse(command);
                c.loadTask(loadedTask);
                if (completed.equals("1")) {
                    loadedTask.get(loadedTask.size() - 1).taskDone();
                }
            }
            return loadedTask;
        } catch (FileNotFoundException e) {
            new File(filePath);
            return loadedTask;
        } catch (IOException e) {
            throw new DukeException("OOPS!! Unable to read saved data safely.");
        }
    }
}
