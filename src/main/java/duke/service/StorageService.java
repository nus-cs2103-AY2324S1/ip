package duke.service;

import duke.exception.DukeStorageException;
import duke.exception.FileCorruptedException;
import duke.tasks.Task;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the storage and retrieval of {@link Task} objects from a local file.
 * <p>
 * This service class provides an abstraction for the storage operations. It manages the tasks
 * in the local storage and offers functionalities such as loading, saving, and deleting tasks.
 * </p>
 */
public class StorageService {
    public static final String PATH_NAME = "./data";
    public static final String FILE_NAME = "./data/duke.txt";
    private final File directory;
    private final File tasksStorageFile;
    private boolean wasFileCorrupted;

    /**
     * Initializes a new StorageService, ensuring the existence of the storage directory and file.
     *
     * @throws DukeStorageException If there's any issue creating or accessing the storage.
     */
    public StorageService() throws DukeStorageException {
        this.directory = new File(PATH_NAME);
        this.tasksStorageFile = new File(FILE_NAME);
        this.wasFileCorrupted = false;
        initDirectory();
        initFile();
    }

    /**
     * Checks if the storage file was corrupted during the last operation.
     *
     * @return {@code true} if the file was corrupted, otherwise {@code false}.
     */
    public boolean wasFileCorrupted() {
        return wasFileCorrupted;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of {@link Task} objects loaded from the storage file.
     */
    public List<Task> loadTasks() {
        try {
            return getTasks(tasksStorageFile);
        } catch (FileCorruptedException e) {
            wasFileCorrupted = true;
            return new ArrayList<>(); // return an empty list if file is corrupted
        }
    }

    /**
     * Saves a single task to the storage.
     *
     * @param task The task to be saved.
     * @throws DukeStorageException If there's an error during the save operation.
     */
    public void saveTask(Task task) throws DukeStorageException {
        List<Task> tasks = loadTasks();
        tasks.add(task);
        writeTasksToFile(tasks);
    }

    /**
     * Saves a list of tasks to the storage.
     *
     * @param taskList The list of tasks to be saved.
     * @throws DukeStorageException If there's an error during the save operation.
     */
    public void saveTasks(List<Task> taskList) throws DukeStorageException {
        writeTasksToFile(taskList);
    }

    /**
     * Deletes a task from the storage based on its index.
     *
     * @param index The index of the task in the storage list.
     * @throws DukeStorageException If there's an error during the deletion operation.
     */
    public void deleteTask(int index) throws DukeStorageException {
        List<Task> tasks = loadTasks();
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            writeTasksToFile(tasks);
        }
    }

    /**
     * Deletes a specified task from the storage.
     *
     * @param task The task to be deleted.
     * @throws DukeStorageException If there's an error during the deletion operation.
     */
    public void deleteTask(Task task) throws DukeStorageException {
        List<Task> tasks = loadTasks();
        tasks.remove(task);
        writeTasksToFile(tasks);
    }

    // Helper methods below
    private void writeTasksToFile(List<Task> tasks) throws DukeStorageException {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
        } catch (IOException e) {
            throw new DukeStorageException(String.format("Failed to write tasks to file!%nError: %s", e.getMessage()));
        }
    }

    private List<Task> getTasks(File file) throws FileCorruptedException {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            @SuppressWarnings("unchecked")
            List<Task> storedTaskList = (List<Task>) ois.readObject();
            return storedTaskList;
        } catch (IOException | ClassNotFoundException e) {
            throw new FileCorruptedException(String.format("Failed to load tasks!%nError: %s", e.getMessage()));
        }
    }

    private void initDirectory() throws DukeStorageException {
        try {
            Files.createDirectories(directory.toPath());
        } catch (IOException e) {
            throw new DukeStorageException(String.format("Failed to create/load directory at %s", PATH_NAME));
        }
    }

    private void initFile() throws DukeStorageException {
        if (!tasksStorageFile.exists()) {
            try {
                Files.createFile(tasksStorageFile.toPath());
            } catch (IOException e) {
                throw new DukeStorageException(String.format("Failed to create/load %s file", FILE_NAME));
            }
        }
    }
}
