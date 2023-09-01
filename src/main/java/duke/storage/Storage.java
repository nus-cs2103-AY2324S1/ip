package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

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

            // Make the directory if not exist
            if(!directory.exists()) {
                directory.mkdir();
            }

            File file = new File(this.filePath);

            // Make the file if not exist
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

        // Adds each task
        while(line != null) {
            Task currentTask = readTask(line);
            loadedTasks.add(currentTask);
            line = reader.readLine();
        }

        reader.close();
        return loadedTasks;
    }
    
    /**
     * Reads a task stored in the storage
     * 
     * @param taskStoredInString the string representative of the task stored in the storage
     * @return the task
     * @throws IOException
     */
    public static Task readTask(String taskStoredInString) throws IOException {
        String[] taskInStringSplit = taskStoredInString.split(" \\| ");
        String type = taskInStringSplit[0];
        String mark = taskInStringSplit[1];
        String description = taskInStringSplit[2];
        Task task = type.equals("T") 
            ? new ToDo(description) 
            : type.equals("D") 
            ? new Deadline(description, LocalDate.parse(taskInStringSplit[3])) 
            : new Event(description, LocalDate.parse(taskInStringSplit[3]), LocalDate.parse(taskInStringSplit[4]));
        if(mark.equals("1")) {
            task.mark();
        }
        return task;
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