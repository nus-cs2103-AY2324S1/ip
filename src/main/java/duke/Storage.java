package duke;

import duke.task.TaskList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a storage that loads tasks from the specified file and saves tasks to the same file.
 */
public class Storage {
    private static final String FILE_PATH = "data/duke.txt";

    public Storage() {
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
            new File(FILE_PATH).delete();
            return;
        }
        assert !txt.isEmpty() : "tasks.txt should not be empty";
        new File(FILE_PATH).createNewFile();
        assert new File(FILE_PATH).exists() : "tasks.txt should exist";
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(taskList.getTasksTxt());
        fw.close();
    }
}
