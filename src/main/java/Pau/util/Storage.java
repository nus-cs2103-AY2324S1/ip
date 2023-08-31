package Pau.util;

import Pau.task.Task;
import Pau.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the file used to store the list of tasks.
 */
public class Storage {
    private final String DEFAULT_STORAGE_FILEPATH;

    public Storage(String filepath) {
        this.DEFAULT_STORAGE_FILEPATH = filepath;
    }
    public TaskList loadTasks() {

        try {
            File toLoad = new File(DEFAULT_STORAGE_FILEPATH);
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

    public void saveTasksToFile(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(DEFAULT_STORAGE_FILEPATH);
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
