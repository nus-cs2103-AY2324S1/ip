import DukeException.*;
import Task.*;

import java.io.File;

public class Storage {
    private String filePath;

    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList LoadTaskList() throws FileNotFoundException {
        return new TaskList();
    }
}
