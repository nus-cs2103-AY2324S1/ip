package Jeoe.Others;

import Jeoe.Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;

public class StorageManager {
    private Scanner scanner;
    private String filePath;

    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    public File load() throws Exception {
        try {
            // add items into the storage when loading the app
            // find the file
            File taskListData = new File(filePath);
            // if file doesnt exist, // taskListData.length() == 0; // is used to check length of file
            if (!taskListData.exists()) {
                System.out.println("task list data doesnt exist");
                taskListData.getParentFile().mkdirs();
                taskListData.createNewFile();
            }
            return taskListData;
        } catch (Exception e) {
            System.out.println("couldn't create the new file exception");
            throw new Exception();
        }
    }

    private static String taskToString(Task t) {
        // splits into type, mark or not, description, from, to
        String deLim = " | ";
        switch (t.taskType()) {
            case TODO:
                String todo = "T" + deLim;
                todo += t.isDone() ? ("1" + deLim) : ("0" + deLim);
                todo += t.getDescription();
                return todo;
            case DEADLINE:
                String deadline = "D" + deLim;
                deadline += t.isDone() ? ("1" + deLim) : ("0" + deLim);
                deadline += t.getDescription() + deLim;
                deadline += t.getEndDateTimeString();
                return deadline;
            case EVENT:
                String event = "E" + deLim;
                event += t.isDone() ? ("1" + deLim) : ("0" + deLim);
                event += t.getDescription() + deLim;
                event += t.getEndDateTimeString();
                return event;
            default:
                return null;
        }
    }

    public void save(ArrayList<Task> tasks) { // for saving into the storage, just rewrite the entire file
        // to overwrite the file
        try {
            // concatenate all the tasks in string form
            String listOfTasksString = "";
            for(Task t : tasks) {
                String tString = taskToString(t);
                listOfTasksString += (tString + "\n");
            }

            // write to the file
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(listOfTasksString);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
