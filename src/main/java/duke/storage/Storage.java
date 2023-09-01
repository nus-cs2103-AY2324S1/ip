package duke.storage;

import duke.TaskList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    private final Path path;

    public Storage(String path) {
        this.path = Path.of(path);
    }

    /**
     * Loads any existing tasks from the cache at the storage path.
     *
     * @return a list of existing tasks loaded from the cache
     * @throws StorageException if there is no existing task cache or cache is of incorrect format
     */
    public TaskList load() throws StorageException {
        if (Files.notExists(path)) {
            throw new StorageException("No existing tasks found");
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(path.toString());
            ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);

            TaskList tasks = (TaskList) objInputStream.readObject();

            objInputStream.close();
            fileInputStream.close();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            try {
                Files.delete(path);
            } catch (IOException ignored) {
            }
            throw new StorageException(String.format("Something went wrong loading existing tasks from %s", path));
        }
    }

    /**
     * Saves all tasks in the task list to the cache at the storage path. Any existing cache file will be overwritten.
     *
     * @param tasks a list of tasks to be saved to the cache
     */
    public void save(TaskList tasks) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path.toString());
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

            objOutputStream.writeObject(tasks);

            objOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ignored) {
        }
    }
}
