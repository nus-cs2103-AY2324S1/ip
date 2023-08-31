import exception.DukeException;
import exception.FileCorruptedException;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class StorageService {
    public static final String PATH_NAME = "./data";
    public static final String FILE_NAME = "./data/duke.txt";
    private final File directory;
    private final File tasksStorageFile;
    private List<Task> tasksCache;
    private boolean wasFileCorrupted;

    public StorageService() throws DukeException {
        this.directory = new File(PATH_NAME);
        this.tasksStorageFile = new File(FILE_NAME);
        this.wasFileCorrupted = false;
        initDirectory();
        initFile();
        initCache();
    }

    public boolean wasFileCorrupted() {
        return wasFileCorrupted;
    }

    public List<Task> loadTasks() throws FileCorruptedException {
        try {
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            if (ois.available() == 0) {
                return new ArrayList<>();
            }

            // It is safe to cast the object returned by readObject() to List<Task>,
            // because the only way to write to the file is through saveTask() or saveTasks(),
            // which can only take in a Task or a List<Task> respectively,
            // and then writes a List<Task> to the file.
            @SuppressWarnings("unchecked")
            List<Task> storedTaskList = (List<Task>) ois.readObject();
            return storedTaskList;
        } catch (IOException | ClassNotFoundException e) {
            throw new FileCorruptedException(String.format("Failed to load tasks!%nError: %s", e.getMessage()));
        }
    }

    public void saveTask(Task task) throws DukeException {
        tasksCache.add(task);
        writeTasksToFile();
    }

    public void saveTasks(List<Task> taskList) throws DukeException {
        tasksCache = taskList;
        writeTasksToFile();
    }

    public void deleteTask(Task task) throws DukeException {
        tasksCache.remove(task);
        writeTasksToFile();
    }

    private void writeTasksToFile() throws DukeException {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasksCache);
        } catch (IOException e) {
            throw new DukeException(String.format("Failed to save tasks to file!%nError: %s", e.getMessage()));
        }
    }

    private void initDirectory() throws DukeException {
        try {
            Files.createDirectories(directory.toPath());
        } catch (IOException e) {
            throw new DukeException(String.format("Failed to create/load directory at %s", PATH_NAME));
        }
    }

    private void initFile() throws DukeException {
        if (!tasksStorageFile.exists()) {
            try {
                Files.createFile(tasksStorageFile.toPath());
            } catch (IOException e) {
                throw new DukeException(String.format("Failed to create/load %s file", FILE_NAME));
            }
        }
    }

    private void initCache() throws DukeException {
        try {
            this.tasksCache = loadTasks();
        } catch (FileCorruptedException e) {
            try {
                Files.newBufferedWriter(tasksStorageFile.toPath()).close(); // this empties the file
            } catch (IOException ex) {
                throw new DukeException("Failed to overwrite corrupted file.");
            }
            wasFileCorrupted = true;
            tasksCache = new ArrayList<>(); // initialize with an empty list after clearing corrupted file
        }
    }
}
