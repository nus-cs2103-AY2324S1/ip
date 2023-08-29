package io;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DukeException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;
import tasks.TodoTask;

public class Storage {
    private String filepath = "data/tasks.txt";

    public Storage(String filePath) {
        this.filepath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> userTasks = new ArrayList<Task>();
        String fileString = "";
        try {
            // check if file exists
            File file = new File(filepath);
            if (!file.exists()) {
                throw new DukeException("File does not exist.");
            }
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                fileString += s.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Loading failed: " + e.getMessage());
        }
        String[] fileStringArray = fileString.split("\n");
        for (int i = 0; i < fileStringArray.length; i++) {
            String[] taskStringArray = fileStringArray[i].split(" \\| ");
            String taskType = taskStringArray[0];
            Task task;
            switch (taskType) {
            case "T":
                task = new TodoTask();
                break;
            case "D":
                task = new DeadlineTask();
                break;
            case "E":
                task = new EventTask();
                break;
            default:
                throw new DukeException("Corrupted file, ensure content is in format.");
            }
            task.fromFileString(fileStringArray[i]);
            userTasks.add(task);
        }
        return userTasks;
    }

    public void load(TaskList taskList) throws DukeException {
        ArrayList<Task> userTasks = this.load();
        taskList.setTasks(userTasks);
    }

    public void save(TaskList taskList) throws DukeException {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("File created: " + file.getName());
            }
            FileWriter fw = new FileWriter(filepath);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Saving failed: " + e.getMessage());
        }
    }

}
