package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

/*
 * Storage class that save the data
 */
public class Storage {
    private final String filePath;


    // Constructor
    /**
     * Constructor for Storage class
     */
    public Storage(String directoryName, String fileName) {
        this.filePath = directoryName + "/" + fileName;
        
        // Make new file to store
        try {
            File directory = new File(directoryName);

            if(!directory.exists()) {
                directory.mkdir();
            }

            File file = new File(this.filePath);

            if(file.exists()) {
                loadTasks();
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
        }
    }

    // Method

    /**
     * Loads the tasks from the storage
     * 
     * @return the tasks loaded
     * @throws IOException
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        String line = reader.readLine();

        while(line != null) {
            String[] lineSplit = line.split(" \\| ");
            String type = lineSplit[0];
            String mark = lineSplit[1];
            String description = lineSplit[2];
            Task currentTask = type.equals("T") 
                ? new ToDo(description) 
                : type.equals("D") 
                ? new Deadline(description, LocalDate.parse(lineSplit[3])) 
                : new Event(description, LocalDate.parse(lineSplit[3]), LocalDate.parse(lineSplit[4]));
            if(mark.equals("1")) currentTask.mark();
            loadedTasks.add(currentTask);
            line = reader.readLine();
        }
        reader.close();
        return loadedTasks;
    }

    /**
     * Method to add task
     * 
     * @param task the task added
     */
    public void addTask(Task task) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(task.storeInString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to add task: " + e.getMessage());
        }
    }

    /**
     * Method to update the storage based on current tasks
     * 
     */
    public void updateTask(TaskList tasks) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(tasks.storeInString());
            writer.close();
        } catch(IOException e) {
            System.out.println("Failed to update task: " + e.getMessage());
        }
    }
}