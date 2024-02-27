package Duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Duke.Tasks.TaskList;
import Duke.Tasks.Task;
import Duke.Tasks.DeadlineTask;
import Duke.Tasks.EventTask;
import Duke.Tasks.ToDoTask;

/**
 * The Storage class is responsible for saving tasks to and loading tasks from a data file.
 */
public class Storage {

    /**
     * Saves tasks from a TaskList to a data file.
     *
     * @param x The TaskList containing tasks to be saved.
     */
    private static final String path = "./data/data.txt";
    public static void saveTasks(TaskList x) {
        try {
            assert x != null;
            File file = new File(path);
            file.getParentFile().mkdirs();
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            x.saveTasksToFile(writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("IO");
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from a data file into an ArrayList of Task objects.
     *
     * @return An ArrayList containing the loaded tasks.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                List<String> splitsy = new ArrayList<>(Arrays.asList(line.split("\\| ")));
                if (splitsy.size() < 4) {
                    splitsy.add("hi");
                }
                tasks.add(Storage.parseTask(splitsy.get(0), splitsy.get(1), splitsy.get(2), splitsy.get(3)));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File");
            e.printStackTrace();
            Storage.createEmptyFile();
        } catch (IOException e) {
            System.out.println("IO");
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Creates an empty data file if it does not exist.
     */
    public static void createEmptyFile() {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
            System.out.println("New file created at path: " + path);
        } catch (IOException e) {
            System.out.println("Error creating the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads the file where the tasks are saved and recreates them by breaking it down to components.
     *
     * @param type The task type as a string (e.g., "[T] ").
     * @param completionStatus The task's completion status as a string (e.g., "[X] " for completed, "[ ] " for not completed).
     * @param taskDescription The description of the task.
     * @param deadline The deadline or additional information associated with the task.
     * @return A Task object representing the parsed task.
     */
    public static Task parseTask(String type, String completionStatus, String taskDescription, String deadline) {
        if (type.equals("[T] ")) {
            Task task = new ToDoTask(taskDescription);
            if (completionStatus.equals("[X] ")) {
                task.markDone();
            }
            return task;
        } else if (type.equals("[E] ")) {
            String from = deadline.split("-")[0];
            String to = deadline.split("-")[1];
            Task task = new EventTask(taskDescription.trim() + " /from " + from.trim() + " /to " + to.trim());
            if (completionStatus.equals("[X] ")) {
                task.markDone();
            }
            return task;
        } else {
            Task task = new DeadlineTask(taskDescription.trim() + " /by " + deadline.trim());
            if (completionStatus.equals("[X] ")) {
                task.markDone();
            }
            return task;
        }
    }
}

