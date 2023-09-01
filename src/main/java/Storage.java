import Exceptions.DukeException;
import Exceptions.InvalidTaskException;
import Exceptions.NoTaskFoundException;
import Tasks.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Storage system of the Duke App. A <code>Storage</code> object stores
 * the location where the data is loaded from
 */
public class Storage {
    String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }


    /**
     * Loads the tasks in the file specified by filepath.
     *
     * @return tasks List of Tasks from the loaded file.
     * @throws NoTaskFoundException If no tasks are found in the filepath or
     * the filepath is not specified or does not exist.
     */
    public List<Task> load() throws DukeException {
        System.out.println("Loading tasks...");
        List<Task> tasks = new ArrayList<Task>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseTaskFromFileString(line);
                tasks.add(task);
            }
            System.out.println("Tasks loaded successfully!");
            return tasks;
        } catch (IOException e) {
            System.out.println("Tasks failed to load");
            throw new NoTaskFoundException();
        }
    }
}
