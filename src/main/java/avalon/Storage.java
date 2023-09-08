package avalon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to/from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into the provided TaskList.
     *
     * @param taskList The TaskList to which tasks will be loaded.
     * @return The TaskList with loaded tasks.
     */
    public TaskList loadTasks(TaskList taskList) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            System.out.print("Loading...");
            while (scanner.hasNext()) {
                String description = scanner.nextLine();
                Task task = TaskParser.parse(description);
                taskList.addTask(task);
            }
            scanner.close();
        } catch (IOException e) {
            // Handle the case where the file doesn't exist or other IO errors
            System.out.println("No existing tasks file found. Starting with an empty list.");
            System.exit(1);
        }
        System.out.println("Tasks loaded successfully!");
        return taskList;
    }

    /**
     * Saves tasks from the provided TaskList to the file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     */
    public void saveTasks(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : taskList.tasks()) {
                String taskData = TaskParser.serialize(task);
                writer.write(taskData + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        }
    }

}
