package grumpygordon.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.exceptions.GrumpyGordonInitialisationException;
import grumpygordon.tasks.Task;
import grumpygordon.tasks.TaskList;

/**
 * Represents a storage for tasks.
 */
public class Storage {

    /**
     * Path to the directory where the data file is stored.
     */
    private static final String DIRECTORY_PATH = "./data";

    /**
     * Path to the data file.
     */
    private static final String FILE_PATH = "./data/tasks.txt";

    /**
     * Constructor of Storage.
     */
    public Storage() throws GrumpyGordonInitialisationException {
        File dataDirectory = new File(DIRECTORY_PATH);
        File dataFile = new File(FILE_PATH);

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new GrumpyGordonInitialisationException("Error: Unable to create new file to store tasks.\n");
            }
        }
    }

    /**
     * Saves the tasks to the data file.
     * @param tasks The list of tasks to be saved
     */
    public void saveTasks(TaskList tasks) {
        try (FileWriter fw = new FileWriter(FILE_PATH);
             BufferedWriter bw = new BufferedWriter(fw)) {
            Task task;
            for (int i = 0; i < tasks.size(); i++) {
                task = tasks.getTask(i);
                String taskString = task.toSaveFormat();
                bw.write(taskString);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the data file.
     * @return The list of tasks loaded from the data file
     */
    public TaskList loadTasks() throws GrumpyGordonException {
        TaskList tasks = new TaskList();
        try (FileReader fr = new FileReader(FILE_PATH);
             BufferedReader br = new BufferedReader(fr)) {
            String taskString;
            while ((taskString = br.readLine()) != null) {
                Task task = Task.fromSaveFormat(taskString);
                tasks.addTask(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks from file: " + e.getMessage());
        }
        return tasks;
    }
}
