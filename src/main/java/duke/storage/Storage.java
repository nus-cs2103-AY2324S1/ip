package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.TaskList;

/**
 * Handles saving and loading of tasks from and to a data text file.
 */
public class Storage {
    /**
     * The default file name for storing tasks on disk.
     */
    public static final String DEFAULT_STORAGE_PATH = ".duke-storage.txt";
    private final Path path;

    public Storage() {
        this(DEFAULT_STORAGE_PATH);
    }

    public Storage(String path) {
        this.path = Paths.get(path);
    }

    /**
     * Saves Duke's task list in a text file.
     *
     * @param taskList the list of tasks to save
     * @throws IOException if an I/O error occurs when writing to the file
     */
    public void save(TaskList taskList) throws IOException {
        List<String> encodedTaskList = taskList.encode();
        Files.write(path, encodedTaskList);
    }

    /**
     * Loads the task list from Duke's data text file.
     *
     * @return the loaded task list from the data text file
     * @throws IOException if an I/O error occurs when reading from the file
     */
    public TaskList load() throws IOException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }
        return TaskList.decode(Files.readAllLines(path));
    }
}
