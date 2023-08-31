package duke;

import java.io.File;
public class Storage {
    File taskList;

    public Storage(String filePath) {
        this.taskList = new File(filePath);
    }

    public File load() {
        return taskList;
    }
}
