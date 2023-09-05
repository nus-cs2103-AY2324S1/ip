package chadbod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * Handles the saving and loading of tasks to/from a file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save the tasks from a TaskList to a file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws ChadBodException If there is an error while saving tasks to the file.
     */
    public void saveTasks(TaskList tasks) throws ChadBodException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                // Create necessary directories and files
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                writer.write(tasks.getTask(i).toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException | SecurityException e) {
            throw new ChadBodException("Error saving tasks to file.");
        }
    }

    /**
     * Load tasks from a file and return them in a TaskList.
     *
     * @return A TaskList containing tasks loaded from the file.
     * @throws ChadBodException If there is an error while loading tasks from the file.
     */
    public TaskList loadTasks() throws ChadBodException {
        TaskList tasks = new TaskList();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                if (task != null) {
                    tasks.addTask(task);
                } else {
                    throw new ChadBodException("File content invalid. Skipping this task.");
                }
            }
        } catch (FileNotFoundException e) {
            throw new ChadBodException("Task storage file not found. Starting with an empty task list.");
        } catch (IOException e) {
            throw new ChadBodException("Error parsing task storage file. Starting with an empty task list.");
        }

        return tasks;
    }
}