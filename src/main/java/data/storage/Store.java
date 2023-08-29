package data.storage;

import exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.File;

import data.task.Task;
import data.task.taskList.TaskList;


public class Store {
    private static Store store = new Store();
    TaskList tasks = new TaskList();
    String fileName = "duke.txt";

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
    }
      

    public static Store getInstance() {
        return store;
    }

    private void write() {   
        try {
             FileWriter fw = new FileWriter(fileName);
             fw.write(tasks.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public void addTask(Task task) throws DukeException {
        tasks.addTask(task);
        write();
    }

    public Task[] getTasks() {
        return tasks.getTasks();
    }

    public Task getTask(int index) throws DukeException{
         return tasks.getTask(index);
    }

    public void deleteTask(int index) throws DukeException{
        tasks.deleteTask(index);
        write();
    }

    public void mark(int index) throws DukeException {
        tasks.mark(index);
        write();
       
    }

    public void unmark(int index) throws DukeException {
        tasks.unmark(index);
        write();
    }

    public void updateDescription(int index, String description) throws DukeException{
        tasks.updateDescription(index, description);
        write(); 
    }  

    public int getTaskCount() {
        return tasks.getTaskCount();
    }

    public boolean hasTaskAtIndex(int index) {
        return tasks.hasTaskAtIndex(index);
    }

    @Override
    public String toString() {
        return tasks.toString();
    } 
}

