package duke.data.storage;

import duke.data.task.taskList.TaskList;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.File;
import java.io.FileReader;

import duke.data.task.Task;
import duke.data.task.builder.TaskBuilder;


public class Store {
    private static Store store = new Store();
    TaskList tasks = new TaskList();
    String fileName = "duke.txt";
    TaskBuilder taskBuilder = new TaskBuilder();
    //FileWriter fw;
    //File file = new File("duke.txt");
    
        

    private Store() {
        // detect whetehr duke.txt exists, and create it if it doesn't
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            int i;
            String input = "";
            while ((i = fr.read()) != -1) {
                input += (char) i;
            }
            String[] inputStrs = input.split("\n");
            for (String inputStr : inputStrs) {
                if (inputStr.equals("")) {
                    continue;
                }
                Task task = taskBuilder.buildFromString(inputStr);
                tasks.addTask(task);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (DukeException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        
    }
      
    /**
     * Returns the instance of Store.
     * @return The instance of Store.
     */
    public static Store getInstance() {
        return store;
    }
     
    private void write() {   
        try {
             FileWriter fw = new FileWriter(fileName);
             fw.write(tasks.getUserInputStrs());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     * @throws DukeException
     */
    public void addTask(Task task) throws DukeException {
        tasks.addTask(task);
        write();
    }
    /**
     * Returns the tasks in the task list.
     * @return The tasks in the task list.
     */
    public Task[] getTasks() {
        return tasks.getTasks();
    }
    
    /**
     * Returns the task at the specified index.
     * @param index The index of the task to be returned.
     * @return The task at the specified index.
     * @throws DukeException If index is invalid.
     */
    public Task getTask(int index) throws DukeException{
         return tasks.getTask(index);
    }
     
    /**
     * Deletes a task from the task list.
     * @param index The index of the task to be deleted.
     * @throws DukeException If index is invalid.
     */
    public void deleteTask(int index) throws DukeException{
        tasks.deleteTask(index);
        write();
    }
    
    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     * @throws DukeException If index is invalid.
     */
    public void mark(int index) throws DukeException {
        tasks.mark(index);
        write();
       
    }
     
    /**
     * Marks a task as not done.
     * @param index The index of the task to be marked as not done.
     * @throws DukeException If index is invalid.
     */
    public void unmark(int index) throws DukeException {
        tasks.unmark(index);
        write();
    }
    
    /**
     * Updates the description of a task.
     * @param index The index of the task to be updated.
     * @param description The new description of the task.
     * @throws DukeException If index is invalid.
     */
    public void updateDescription(int index, String description) throws DukeException{
        tasks.updateDescription(index, description);
        write(); 
    }  
    
    /**
     * Updates the date of a task.
     * @param index The index of the task to be updated.
     * @param date The new date of the task.
     * @throws DukeException If index is invalid.
     */
    public int getTaskCount() {
        return tasks.getTaskCount();
    }
    
    /**
     * Returns the number of tasks in the task list.
     * @return The number of tasks in the task list.
     */
    public boolean hasTaskAtIndex(int index) {
        return tasks.hasTaskAtIndex(index);
    }
    
    @Override
    public String toString() {
        return tasks.toString();
    } 

    public TaskList find(String keyword) throws DukeException {
        return tasks.findTasksWithKeyword(keyword);
    }
}

