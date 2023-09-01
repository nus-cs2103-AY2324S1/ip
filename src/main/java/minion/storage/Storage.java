package minion.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import minion.common.Messages;
import minion.data.TaskList;
import minion.data.task.Task;
import minion.parser.FileParser;

/**
 * Represents storage of the chatbot.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a storage object.
     * @param filePath Path of file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a directory and the file inside it.
     * @param file the file object.
     * @throws IOException when fail to create directory / file.
     */
    private void createFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Loads a list of tasks from the file. If the directory/file is not present, it will be created,
     * before throwing an exception.
     * @return a list of tasks from the file.
     * @throws IOException when fail to create directory / file.
     */
    public List<Task> load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            createFile(file);
            throw new FileNotFoundException(Messages.MESSAGE_FILE_NOT_FOUND);
        }
        return FileParser.parse(file);
    }

    /**
     * Writes a list of tasks to the file.
     * @param tasks the TaskList.
     * @throws IOException when failed write to file.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(tasks.toStringStorage());
        fw.close();
    }
}
