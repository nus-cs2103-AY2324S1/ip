package storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import exceptions.DukeStorageException;
import tasks.Task;
import tasks.TaskList;


/**
 * The `TaskFileHandler` class is responsible for reading and writing tasks to a JSON file.
 */
public class TaskFileHandler {
    /**
     * The file path where tasks are stored as JSON data.
     */
    private static final String FILE_PATH = "data/tasks.json";

    /**
     * Reads tasks from the JSON file and returns a `TaskList` object.
     *
     * @return A `TaskList` containing the tasks read from the file.
     * @throws DukeStorageException If there is an issue with reading or parsing the file.
     */
    public static TaskList readFromFile() {
        createFileIfNotExists();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        Task[] tasks;
        try (FileReader r = new FileReader(FILE_PATH)) {
            tasks = gson.fromJson(r, Task[].class);
        } catch (IOException e) {
            throw new DukeStorageException("Error reading from file: " + e.getMessage());
        }

        return new TaskList(tasks);
    }

    /**
     * Saves tasks to the JSON file.
     *
     * @param taskList The `TaskList` containing tasks to be saved.
     * @throws DukeStorageException If there is an issue with writing to the file.
     */
    public static void saveToFile(TaskList taskList) {
        Task[] tasks = taskList.getTasks();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        try (FileWriter w = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, w);
        } catch (IOException e) {
            throw new DukeStorageException("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Creates the JSON file if it does not exist.
     *
     * @throws DukeStorageException If there is an issue with file creation.
     */
    private static void createFileIfNotExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeStorageException("Error creating file: " + e.getMessage());
            }
        }
    }
}
