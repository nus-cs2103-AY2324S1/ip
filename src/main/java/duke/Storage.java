package duke;

import duke.task.TaskList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a storage that loads tasks from the specified file and saves tasks to the same file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file path. Create the folder if necessary.
     */
    public File loadFile() {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        return new File(folder, "duke.txt");
    }

    /**
     * Saves the specified task list to the specified file path. Create the file if necessary.
     * @throws IOException
     */
    public void save(TaskList taskList) throws IOException {
        String txt = taskList.getTasksTxt();
        if (txt.isEmpty()) {
            return;
        }
        assert !txt.isEmpty() : "tasks.txt should not be empty";
        new File(filePath).createNewFile();
        assert new File(filePath).exists() : "tasks.txt should exist";
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskList.getTasksTxt());
        fw.close();
    }
}
