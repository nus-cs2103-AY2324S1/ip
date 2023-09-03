package functions;

import java.io.IOException;

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

    public void save(TaskList taskList) throws IOException {
        Save save = new Save(taskList, this.filePath);
    }
}
