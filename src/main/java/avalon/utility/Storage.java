package avalon.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import avalon.task.Task;
import avalon.task.TaskList;



/**
 * Handles the loading and saving of tasks to/from a file.
 */
public class Storage {
    private final String filePath;

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
     */
    public void loadTasks(TaskList taskList) {
        try {
            File file = new File(filePath);
            Scanner scanner;
            System.out.print("Loading...");

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("No existing Avalon.txt found. File created!");
                // Close scanner and open a new one on the created file
                scanner = new Scanner(file);
            } else {
                scanner = new Scanner(file);
            }

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
    }

    /**
     * Saves tasks from the provided TaskList to the file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     */
    public void saveTasks(TaskList taskList) {
        try {
            File file = new File(filePath);
            assert file.canWrite() : "Cannot write to tasks file";
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
