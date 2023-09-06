package bouncybob.util;

import java.util.List;
import java.util.ArrayList;

import bouncybob.task.Deadlines;
import bouncybob.task.Events;
import bouncybob.task.Task;
import bouncybob.task.ToDos;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for handling file operations related to tasks.
 */
public class TaskFileHandler {
    private static final String FILE_PATH = "./data/bouncy.txt";

    /**
     * Saves the current tasks to disk.
     *
     * @param taskList The list of tasks to be saved.
     */
    public static void saveTasksToDisk(TaskList taskList) {
        saveTasksToFile(taskList.getTasks());
    }

    public static void saveTasksToDisk(ObservableList<Task> taskList) {
        saveTasksToFile(new ArrayList<>(taskList));
    }

    private static void saveTasksToFile(List<Task> tasks) {
        StringBuilder builder = new StringBuilder();
        for (Task task : tasks) {
            builder.append(task.toFileFormat()).append("\n");
        }

        File file = new File(FILE_PATH);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(builder.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from disk into the provided task list.
     *
     * @param taskList The list where tasks will be loaded.
     */
    public static void loadTasksFromDisk(TaskList taskList) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String name = parts[2].trim();

                Task task = null;
                switch (type) {
                    case "T":
                        task = new ToDos(name);
                        break;
                    case "D":
                        String datetime = parts[3].trim();
                        task = new Deadlines(name, datetime);
                        break;
                    case "E":
                        String from = parts[3].trim();
                        String to = parts[4].trim();
                        task = new Events(name, from, to);
                        break;
                }
                if (task != null) {
                    if (isDone) {
                        task.setDone();
                    }
                    taskList.addTask(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
