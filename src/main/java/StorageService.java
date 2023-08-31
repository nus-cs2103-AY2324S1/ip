import exception.DukeStorageException;
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
    private boolean wasFileCorrupted;

    public StorageService() throws DukeStorageException {
        this.directory = new File(PATH_NAME);
        this.tasksStorageFile = new File(FILE_NAME);
        this.wasFileCorrupted = false;
        initDirectory();
        initFile();
    }

    public boolean wasFileCorrupted() {
        return wasFileCorrupted;
    }

    public List<Task> loadTasks() {
        try {
            return getTasks(tasksStorageFile);
        } catch (FileCorruptedException e) {
            wasFileCorrupted = true;
            return new ArrayList<>(); // return an empty list if file is corrupted
        }
    }

    public void saveTask(Task task) throws DukeStorageException {
        List<Task> tasks = loadTasks();
        tasks.add(task);
        writeTasksToFile(tasks);
    }

    public void saveTasks(List<Task> taskList) throws DukeStorageException {
        writeTasksToFile(taskList);
    }

    public void deleteTask(int index) throws DukeStorageException {
        List<Task> tasks = loadTasks();
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            writeTasksToFile(tasks);
        }
    }

    public void deleteTask(Task task) throws DukeStorageException {
        List<Task> tasks = loadTasks();
        tasks.remove(task);
        writeTasksToFile(tasks);
    }

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
