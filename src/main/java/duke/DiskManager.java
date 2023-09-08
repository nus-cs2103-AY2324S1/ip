package duke;

import task.Deadline;
import task.Event;
import task.Todo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;

/**
 * Represents the disk manager which handles operation related to disk storage.
 */
public class DiskManager {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.registerSubtypes(new NamedType(Todo.class, "Todo"));
        MAPPER.registerSubtypes(new NamedType(Deadline.class, "Deadline"));
        MAPPER.registerSubtypes(new NamedType(Event.class, "Event"));
        MAPPER.registerModule(new JavaTimeModule());
    }
    private String directoryPath;
    private String fileName;

    /**
     * Constructs a DiskManager with a directory path and file name, all storage operation
     * will then operate on the file path specified.
     *
     * @param directoryPath The path to the directory of the storage file.
     * @param fileName The file name of the storage file.
     */
    public DiskManager(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    private File getFile() {
        String filePath = directoryPath + "/" + fileName;
        try {
            String currentWorkingDir = System.getProperty("user.dir");

            // create a file object for the directory
            File directory = new File(currentWorkingDir, directoryPath);

            // If the directory does not exist, create it
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create a File object for the file within the directory
            File file = new File(currentWorkingDir, filePath);

            // If the file doesn't exist, create a new one
            if (!file.exists()) {
                file.createNewFile();
            }

            assert file != null : "file returned by getFile() should not be null";
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String taskManagerToJson(TaskManager taskManager) throws DukeException {
        assert taskManager != null : "taskManager should not be null when converting to JSON format";
        try {
            return MAPPER.writeValueAsString(taskManager);
        } catch (JsonProcessingException e) {
            throw new DukeException("Error when saving task list to local disk.");
        }
    }

    /**
     * Saves the task manager that encapsulates the task list to the disk.
     *
     * @param taskManager The task manager to be saved to disk.
     * @throws DukeException If taskManager could not be serialized into json or FileWriter failed to write to the disk.
     */
    public void saveToDisk(TaskManager taskManager) throws DukeException {
        assert taskManager != null : "taskManager should not be null when saving to disk";
        try {
            String json = taskManagerToJson(taskManager);
            File file = getFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error when writing to local disk.");
        }
    }

    /**
     * Loads data from the disk and returns the task manager encapsulating the data.
     *
     * @return The task manager.
     * @throws DukeException If could not read file from disk or could not deserialize the data read from disk.
     */
    public TaskManager loadFromDisk() throws DukeException {
        try {
            File file = getFile();

            // read the file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            String json = jsonData.toString();
            if (json.isEmpty()) {
                return new TaskManager();
            }

            TaskManager taskManager = MAPPER.readValue(json, TaskManager.class);
            assert taskManager != null : "taskManger loaded from disks should not be null";
            return taskManager;
        } catch (JsonProcessingException e) {
            throw new DukeException("Error when deserializing file");
        } catch (IOException e) {
            throw new DukeException("Error when reading file");
        }
    }
}
