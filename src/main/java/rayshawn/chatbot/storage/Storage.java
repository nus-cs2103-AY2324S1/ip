package rayshawn.chatbot.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import rayshawn.chatbot.exceptions.ChatBotException;
import rayshawn.chatbot.tasks.TaskList;

public class Storage {
    public static final String DEFAULT_STORAGE_FILEPATH = "./data/tasklist.txt";

    public final Path path;

    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt");
        }
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    public void save(TaskList tasklist) throws StorageOperationException {
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(tasklist);
            Files.write(path, encodedTaskList);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    public TaskList load() throws StorageOperationException {
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

    public String getPath() {
        return path.toString();
    }

    public static class InvalidStorageFilePathException extends ChatBotException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

}