package grumpygordon.storage;


import grumpygordon.tasks.*;
import grumpygordon.exceptions.*;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Storage {
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = "./data/grumpygordon.tasks.txt";
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
                throw new GrumpyGordonInitialisationException("Error: Unable to create new file to store grumpygordon.tasks.\n");
            }
        }
    }

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
            System.out.println("Error saving grumpygordon.tasks to file: " + e.getMessage());
        }
    }
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
            System.out.println("Error reading grumpygordon.tasks from file: " + e.getMessage());
        }
        return tasks;
    }
}
