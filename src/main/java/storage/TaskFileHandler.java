package storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.ExceptionMessage;
import enums.FilePath;
import exceptions.WoofStorageException;
import tasks.Task;
import tasks.TaskList;


/**
 * The `TaskFileHandler` class is responsible for reading and writing tasks to a JSON file.
 */
public class TaskFileHandler {
    /**
     * Reads tasks from the JSON file and returns a `TaskList` object.
     *
     * @return A `TaskList` containing the tasks read from the file.
     * @throws WoofStorageException If there is an issue with reading or parsing the file.
     */
    public static TaskList readFromFile() {
        createFileIfNotExists();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        Task[] tasks;
        try (FileReader r = new FileReader(FilePath.DEFAULT_STORAGE_PATH.toValue())) {
            tasks = gson.fromJson(r, Task[].class);
        } catch (IOException e) {
            throw new WoofStorageException(ExceptionMessage.UNABLE_TO_READ_FILE.toFormattedString(e.getMessage()));
        }

        return new TaskList(tasks);
    }

    /**
     * Saves tasks to the JSON file.
     *
     * @param taskList The `TaskList` containing tasks to be saved.
     * @throws WoofStorageException If there is an issue with writing to the file.
     */
    public static void saveToFile(TaskList taskList) {
        Task[] tasks = taskList.getTasks();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        try (FileWriter w = new FileWriter(FilePath.DEFAULT_STORAGE_PATH.toValue())) {
            gson.toJson(tasks, w);
        } catch (IOException e) {
            throw new WoofStorageException(ExceptionMessage.UNABLE_TO_SAVE_FILE.toFormattedString(e.getMessage()));
        }
    }

    /**
     * Creates the JSON file if it does not exist.
     *
     * @throws WoofStorageException If there is an issue with file creation.
     */
    private static void createFileIfNotExists() {
        File file = new File(FilePath.DEFAULT_STORAGE_PATH.toValue());
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new WoofStorageException(
                    ExceptionMessage.UNABLE_TO_CREATE_FILE.toFormattedString(e.getMessage())
                );
            }
        }
    }
}
