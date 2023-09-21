package chatterbot.data;

import chatterbot.storage.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks that have been added.
 */
public class TaskList {

    public static ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * This method retrieves tasks from the ChatterBot.txt and loads them up into the task list.
     * @param storage This is where the file and list contents are edited.
     */
    public static void initiateTaskList(Storage storage) {
        try {
            storage.copyFileContents("data/ChatterBot.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * This method converts the current task list to a string.
     * @param list This is the current task list.
     * @return String This is the formatted task list.
     */
    public static String convertToString(ArrayList<Task> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : list) {
            stringBuilder.append(task.formatForFile()).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public String addTask(Task task, Storage storage, String filePath) {
        list.add(task);
        System.out.println(list);
        try {
            // Save the updated task list to the storage file.
            storage.appendToFile(filePath, task.formatForFile());
        } catch (IOException e) {
            return "Error: File not found.";
        }
        return "Got it. I've added this task:\n" + task.formatForFile() + "\nNow you have " + list.size() + " tasks in the list.";
    }

    public String deleteTask(int taskIndex, Storage storage, String filePath) {
//        if (taskIndex < 0 || taskIndex >= list.size()) {
//            return "Invalid task index. No task removed.";
//        }
        try {
            list.remove(taskIndex);
        } catch (Exception e) {
            return "Error, task not removed.";
        }
        try {
            // Save the updated task list to the storage file.
            storage.writeToFile(filePath, convertToString(list));
        } catch (IOException e) {
            return "Error: File not found.";
        }
        return "Noted. I've removed this task:\n" + list.get(taskIndex).formatForFile() + "\nNow you have " + list.size() + " tasks in the list.";
    }
}
