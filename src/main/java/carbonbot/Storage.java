package carbonbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import carbonbot.exception.CarbonSerializationException;
import carbonbot.exception.CarbonStorageException;
import carbonbot.task.Task;
import carbonbot.task.TaskDeserializer;


/**
 * Storage provides operations to write and read from the disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage to interact with the file at the provided file path.
     *
     * @param filePath Path to the file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list to the storage.
     * @param tasks TaskList object.
     * @throws CarbonStorageException If an IO error is encountered while writing to the disk.
     */
    public void saveTasks(TaskList tasks) throws CarbonStorageException {
        try {
            this.write(tasks.serialize());
        } catch (IOException | SecurityException ex) {
            throw new CarbonStorageException(ex.getMessage());
        }
    }

    /**
     * Fetches the tasks from the storage.
     * @throws CarbonStorageException If an IO error is encountered while reading from the disk.
     */
    public TaskList getTasks() throws CarbonStorageException, CarbonSerializationException {
        List<String> lines;
        try {
            lines = this.load();
        } catch (IOException | SecurityException ex) {
            throw new CarbonStorageException(ex.getMessage());
        }

        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            tasks.add(TaskDeserializer.deserialize(line));
        }

        return new TaskList(tasks);
    }

    /**
     * Loads the data from the file.
     *
     * @return The lines in the file in a List.
     * @throws IOException If I/O error occurs reading from the file.
     */
    public List<String> load() throws IOException, SecurityException {
        // Returns an empty list if the file does not exist
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        return Files.readAllLines(Paths.get(filePath));
    }

    /**
     * Writes data to the file. Note that it overwrites instead of append to file.
     *
     * @param data The data to be written in the file.
     * @throws IOException If the data could not be written to the file.
     */
    public void write(String data) throws IOException, SecurityException {
        // Create the file, and its directories if it does not already exist
        File file = new File(filePath);
        if (!file.exists()) {
            // Creates the required directories
            file.getParentFile().mkdirs();
            // Creates the specified file at the file path
            file.createNewFile();
        }

        // Writes the data to the file
        // FileWriter will be closed even if exception occurs during write()
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(data);
        }
    }
}
