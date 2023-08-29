package data;

import exception.DukeException;
import exception.InvalidInputException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.File;


public class Store {
    private static Store store = new Store();
    Task[] tasks = new Task[100];
    int taskCount = 0;
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
            for (int i = 0; i < taskCount; i++) {
                fw.write(tasks[i].toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public void addTask(Task task) throws DukeException {
        if (taskCount == 100) {
            throw new DukeException("task list is full");
        }
        tasks[taskCount] = task;
        taskCount++;
        write();
    }

    public Task[] getTasks() {
        return tasks;
    }

    public Task getTask(int index) throws DukeException{
        if (index > taskCount || index < 1) {
            throw new DukeException("index out of bounds when calling getTask from store");
        }
        return tasks[index - 1];
    }

    public void deleteTask(int index) throws DukeException{
        if (index > taskCount || index < 1) {
            throw new InvalidInputException("index out of bounds");
        }
        for (int i = index - 1; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        taskCount--;
        write();
    }

    public void mark(int index) throws DukeException {
        if (index > taskCount || index < 1) {
           throw new InvalidInputException("index out of bounds");
        }
        tasks[index-1].mark();
        write();
       
    }

    public void unmark(int index) throws DukeException {
        if (index > taskCount || index < 1) {
            throw new InvalidInputException("index out of bounds");
        }

        tasks[index-1].unmark(); 
        write();
    }

    public void updateDescription(int index, String description) throws DukeException{
        if (index > taskCount || index < 1) {
            throw new InvalidInputException("index out of bounds");
        }
        tasks[index-1].setDescription(description); 
        write(); 
    }  

    public int getTaskCount() {
        return taskCount;
    }

    public boolean hasTaskAtIndex(int index) {
        return index <= taskCount && index > 0;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskCount; i++) {
            result += (i + 1) + ". " + tasks[i] + "\n";
        }
        return result;
    }

   
}

