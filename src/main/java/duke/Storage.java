package duke;

import java.io.File;

/**
 * The Storage class represents a storage system for task data.
 * It provides methods to handle loading and saving task data to/from a file.
 */
public class Storage {
    File taskList;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file used for storing task data.
     */
    public Storage(String filePath) {
        this.taskList = new File(filePath);
    }

    /**
     * Loads task data from the file.
     *
     * @return The file containing the task data.
     */
    public File load() {
        return taskList;
    }
}
