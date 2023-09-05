package buddy.utils;

import buddy.*;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath = "./data/tasks.txt";;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList readFile() { // load tasks
        try {
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                TaskList taskList = new TaskList();
                while ((line = reader.readLine()) != null) {
                    taskList = parseTask(line);
                }
                reader.close();
                return taskList;
            }
        } catch (IOException | BuddyException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return new TaskList();
    }

    public void writeToFile(TaskList tasks) { // save tasks
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                fileWriter.write(task.toSaveFileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private TaskList parseTask(String line) throws BuddyException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        TaskList tasks = new TaskList();

        switch (taskType) {
            case "T":
                tasks.addTask(new Todo(description, isDone));
                break;

            case "D":
                String deadlineBy = parts[3];
                tasks.addTask(new Deadline(description, deadlineBy, isDone));
                break;

            case "E":
                String start = parts[3];
                String end = parts[4];
                tasks.addTask(new Event(description, start, end, isDone));
                break;
        }

        return tasks;
    }
}
