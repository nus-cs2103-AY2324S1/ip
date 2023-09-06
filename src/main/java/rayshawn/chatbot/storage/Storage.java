package rayshawn.chatbot.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import rayshawn.chatbot.exceptions.ChatBotException;
import rayshawn.chatbot.tasks.TaskList;

/**
 * Represents the file used to store task list data.
 */
public class Storage {

    public static final String DEFAULT_STORAGE_FILEPATH = "./data/tasklist.txt";

    public final Path path;

    /**
     * Constructor for Storage with default path.
     *
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Constructor for Storage with default path
     *
     * @param filePath filepath to store the task list
     * @throws InvalidStorageFilePathException if the path specified is invalid
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt");
        }
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Saves the task list data to storage file.
     *
     * @param tasklist list to be saved
     * @throws StorageOperationException if there were any errors converting and/or storing data to file.
     */
    public void save(TaskList tasklist) throws StorageOperationException {
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(tasklist);
            Files.write(path, encodedTaskList);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    /**
     * Loads the task list data from the storage file, and the returns it.
     * Returns an empty task list if the file does not exist, or is not a regular file.
     *
     * @return task list from storage file
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public TaskList loadTasks() throws StorageOperationException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }

        try {
            return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (ChatBotException e) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends ChatBotException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occurred while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

}
