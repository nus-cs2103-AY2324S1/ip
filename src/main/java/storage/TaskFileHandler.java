package storage;

import Exceptions.DukeStorageException;
import Tasks.TaskList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Tasks.Task;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskFileHandler {
    private static final String FILE_PATH = "data/tasks.json";

    public static ArrayList<Task> readFromFile() {
        createFileIfNotExists(); // Create the file if it doesn't exist
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskAdapter())
                .create();
        Task[] tasks;
        try (FileReader r = new FileReader(FILE_PATH)) {
            tasks = gson.fromJson(r, Task[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        if (tasks == null) {
            return new ArrayList<>(1);
        } else {
            return new ArrayList<>(Arrays.asList(tasks));
        }
    }

    public static void saveToFile(TaskList taskList) {
        Task[] tasks = taskList.getTasks();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskAdapter())
                .create();
        try (FileWriter w = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createFileIfNotExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); // Create parent directories if necessary
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeStorageException("Error creating file: " + e.getMessage());
            }
        }
    }
}
