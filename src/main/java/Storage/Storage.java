package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import parser.Parser;
import task.Task;
import taskList.TaskList;

/**
 * The `Storage` class is responsible for managing the storage and retrieval of tasks in the BloopBot application.
 * It handles saving tasks to a file and loading tasks from a file.
 * This class ensures that tasks are persisted between program runs.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Storage { // Previously named TaskListManager, serves the same purpose
    private File file;
    private String taskDataPath;
    private TaskList taskList;

    /**
     * Constructs a new `Storage` object with the specified directory path, file name, and `TaskList`.
     *
     * @param directoryPath The directory path where the task data file will be stored.
     * @param fileName      The name of the task data file.
     * @param taskList      The `TaskList` containing tasks to be managed by the storage.
     */
    public Storage(String directoryPath, String fileName, TaskList taskList) {
        this.taskList = taskList;
        this.taskDataPath = directoryPath + "/" + fileName;

        try {
            if (new File(directoryPath).mkdirs()) {
                System.out.println("Directories are created.");
            } else {
                System.out.println("Directories already exist.");
            }

            this.file = new File(taskDataPath);

            if (this.file.createNewFile()) {
                System.out.println("File is created: " + this.file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the provided list of tasks to the task data file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTask(ArrayList<Task> tasks) {
        try {
            FileWriter w = new FileWriter(taskDataPath);
            for (Task task : tasks) {
                w.write(task.toString() + "\n");
            }
            w.close();
        } catch (IOException e) {
            System.out.println("Error occurred when saving tasks to the file.");
        }
    }

    /**
     * Loads tasks from the task data file and adds them to the `TaskList`.
     */
    public void loadTasks() {
        try {
            File file = new File(taskDataPath);
            if (!file.exists()) {
                System.out.println("No such file");
                return;
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                Task task = Parser.parseTask(taskData);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error occurred when loading tasks from the file.");
        } catch (DukeException e) {
            System.out.println("File is corrupted :(");
        }
    }
}
