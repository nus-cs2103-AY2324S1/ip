package milbot;
import taskclasses.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Storage class handles reading tasks from and writing tasks to a file.
 */
public class Storage {
    private File file;
    private FileWriter tasksOutput;
    private String filePath;
    private TaskList taskList;

    /**
     * Constructs a Storage instance with a default file path.
     */
    public Storage() {
        filePath = "src\\main\\data\\mil.txt";
        file = new File(filePath);
        taskList = new TaskList();
    }

    /**
     * Loads tasks from the file and adds them to the task list.
     */
    public TaskList loadTasksFromFile() {
        try (BufferedReader inputFile = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = inputFile.readLine()) != null) {
                Task task = Task.createTaskFromData(line);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Saves tasks from the provided TaskList to the file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     */
    public void saveTasksToFile(TaskList tasks) {
        try {
            taskList = tasks;
            tasksOutput = new FileWriter(filePath);
            BufferedWriter outputFile = new BufferedWriter(tasksOutput);
            for (Task task : tasks.getTaskList()) {
                outputFile.write(task.formatToFile());
                outputFile.newLine();
            }
            outputFile.close();
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
}
