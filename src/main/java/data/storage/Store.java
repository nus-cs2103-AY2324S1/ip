package data.storage;

import exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.File;
import java.io.FileReader;

import data.task.Task;
import data.task.taskList.TaskList;
import data.task.builder.TaskBuilder;


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

    public TaskList find(String keyword) throws DukeException {
        return tasks.findTasksWithKeyword(keyword);
    }
}

