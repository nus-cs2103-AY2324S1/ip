package storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import enums.ExceptionMessage;
import enums.FilePath;
import exceptions.WoofStorageException;
import tasks.Task;
import tasks.TaskList;


/**
 * The `TaskFileHandler` class is responsible for reading and writing tasks to a JSON file.
 */
public class TaskFileHandler {
    private static final String storageLocation = FilePath.DEFAULT_STORAGE_PATH.toValue();
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
        try (FileReader r = new FileReader(storageLocation)) {
            tasks = gson.fromJson(r, Task[].class);
        } catch (IOException e) {
            throw new WoofStorageException(ExceptionMessage.UNABLE_TO_READ_FILE.toFormattedValue(e.getMessage()));
        } catch (JsonSyntaxException | DateTimeException e) {
            return destroyFileAndRetry();
        }

        return new TaskList(tasks);
    }

    /**
     * Destroys the file content and retries reading tasks from the JSON file.
     *
     * @return A `TaskList` containing the tasks after destroying the file content and retrying.
     * @throws WoofStorageException If there is an issue with destroying the file content or reading tasks.
     */
    private static TaskList destroyFileAndRetry() {
        try (FileWriter w = new FileWriter(storageLocation)) {
            w.write("");
        } catch (IOException e) {
            throw new WoofStorageException(ExceptionMessage.UNABLE_TO_UPDATE_FILE.toFormattedValue(e.getMessage()));
        }

        try (FileReader r = new FileReader(storageLocation)) {
            Task[] tasks = new Gson().fromJson(r, Task[].class);
            return new TaskList(tasks);
        } catch (IOException e) {
            throw new WoofStorageException(ExceptionMessage.UNABLE_TO_UPDATE_FILE.toFormattedValue(e.getMessage()));
        }
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
        try (FileWriter w = new FileWriter(storageLocation)) {
            gson.toJson(tasks, w);
        } catch (IOException e) {
            throw new WoofStorageException(ExceptionMessage.UNABLE_TO_UPDATE_FILE.toFormattedValue(e.getMessage()));
        }
    }

    /**
     * Creates the JSON file if it does not exist.
     *
     * @throws WoofStorageException If there is an issue with file creation.
     */
    private static void createFileIfNotExists() {
        File file = new File(storageLocation);
        if (!file.exists()) {
            try {
                if (!file.getParentFile().mkdirs()) {
                    throw new WoofStorageException(
                        ExceptionMessage.UNABLE_TO_CREATE_DIRECTORY.toFormattedValue()
                    );
                }
                if (!file.createNewFile()) {
                    throw new WoofStorageException(
                        ExceptionMessage.UNABLE_TO_CREATE_FILE.toFormattedValue()
                    );
                }
            } catch (IOException e) {
                throw new WoofStorageException(
                    ExceptionMessage.UNABLE_TO_CREATE_FILE.toFormattedValue(e.getMessage())
                );
            }
        }
    }
}
