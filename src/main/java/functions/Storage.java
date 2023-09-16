package functions;

import java.io.IOException;

/**
 * A utility class for storing tasks.
 */
public class Storage {
    private String filePath;

    /**
     * A public constructor to initialize a new storage instance
     *
     * @param filePath file path to load
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public TaskList load() throws IOException {
        Load load = new Load(this.filePath);
        return load.load();
    }

    /**
     * A function to save the current taskList into a .txt file specified by user
     *
     * @param taskList  a task list to store tasks
     */
    public void save(TaskList taskList) {
        Save save = new Save(taskList, this.filePath);
    }
}
