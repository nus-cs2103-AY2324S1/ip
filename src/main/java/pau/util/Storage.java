package pau.util;

import pau.task.Task;
import pau.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    
    /**
     * The path to the file containing tasks to be loaded.
     */
    private String filepath;

    /**
     * Constructs a Storage.
     *
     * @param filepath The path to the file containing tasks to be loaded.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The list of tasks.
     */
    public TaskList loadTasks() {
        try {
            File toLoad = new File(filepath);
            Scanner scan = new Scanner(toLoad);
            TaskList list = new TaskList();
            while (scan.hasNext()) {
                String input = scan.nextLine();
                list.createTask(input);
            }
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("is this your first time with pau?");
        }
        return new TaskList();
    }

    /**
     * Saves tasks to file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasksToFile(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filepath);
            for(int i = 0; i < tasks.listSize(); i++) {
                Task task = tasks.getTask(i);
                writer.write(task.writeToFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("problem saving to file: " + e.getMessage());
        }
    }
}
