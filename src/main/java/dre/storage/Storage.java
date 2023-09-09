package dre.storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import dre.task.Task;
import dre.parser.Parser;
import dre.task.TaskList;
import dre.exception.DreException;

/**
 * Manages loading and saving of tasks to a file.
 */
public class Storage {

    private final String dataFilePath;

    /**
     * Creates a new storage manager.
     *
     * @param filePath The path to the file for saving/loading tasks.
     */
    public Storage(String filePath) {
        this.dataFilePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            File file = new File(dataFilePath);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(dataFilePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        Task task = Parser.parseTask(line);
                        if (task != null) list.add(task);
                    }
                }
            } else {
                System.out.println("No saved tasks found.");
            }
        } catch (IOException | SecurityException e) {
            System.out.println("Error saving tasks to the file.");
            throw new RuntimeException(
                    "This should never happen, I know this file exists", e);
        }
        return list;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param taskList The list of tasks to save.
     */
    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFilePath))) {
            for (int i = 0; i < taskList.size(); i++) {
                // since getTask uses an index starting from 1
                Task task = taskList.getTask(i + 1);
                writer.write(task.fileSaveFormat());
                writer.newLine();
            }
        } catch (IOException | DreException e) {
            System.out.println("Error saving tasks to the file.");
            throw new RuntimeException(
                    "This should never happen, I know this file exists", e);
        }
    }
}
