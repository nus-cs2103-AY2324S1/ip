package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.parser.FileParser;
import duke.task.Task;
import duke.task.TaskList;
import duke.templates.MessageTemplates;

/**
 * Represents the Storage.
 */
public class Storage {
    private final String filePath;
    /**
     * Constructor for Storage.
     * @param filePath Path to file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Creates a file.
     * @param f File to be created.
     * @throws IOException If file cannot be created.
     */
    private void createFile(File f) throws IOException {
        f.getParentFile().mkdirs();
        f.createNewFile();
    }
    /**
     * Loads file.
     * @return ArrayList of Tasks.
     * @throws IOException If file cannot be loaded.
     */
    public ArrayList<Task> loadFile() throws IOException {
        File f = new File(this.filePath);
        if (!f.exists()) {
            createFile(f);
            throw new FileNotFoundException(MessageTemplates.MESSAGE_FILE_NOT_FOUND);
        }
        return FileParser.parse(f);
    }
    /**
     * Writes to file.
     * @param tl TaskList to be written.
     * @throws IOException If file cannot be written.
     */
    public void writeToFile(TaskList tl) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(tl.toStringStore());
        fw.close();
    }
}
