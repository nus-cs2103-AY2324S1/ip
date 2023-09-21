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

    private final String DATA_FILE_PATH;

    /**
     * Creates a new storage manager.
     *
     * @param filePath The path to the file for saving/loading tasks.
     */
    public Storage(String filePath) {
        this.DATA_FILE_PATH = filePath;
        ensureDataFolderExists();
        ensureDataFileExists();
    }

    /**
     * Creates a folder of the given filepath if the folder does not exist.
     */
    private void ensureDataFolderExists() {
        File folder = new File(DATA_FILE_PATH).getParentFile();
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /**
     * Creates a file of the given file path if the file doesn't exist.
     */
    private void ensureDataFileExists() {
        File file = new File(DATA_FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            File file = new File(DATA_FILE_PATH);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
            for (int i = 1; i <= taskList.size(); i++) {
                //TaskList indexing starts from 1.
                Task task = taskList.getTask(i);
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
