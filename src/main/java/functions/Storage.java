package functions;

import java.io.IOException;

public class Storage {
    private String filePath;

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
