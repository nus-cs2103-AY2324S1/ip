package chatterbot.data;

import chatterbot.storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks that have been added.
 */
public class TaskList {

    private static ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * This method retrieves tasks from the ChatterBot.txt and loads them up into the task list.
     * @param storage This is where the file and list contents are edited.
     */
    public static void initiateTaskList(Storage storage) {
        try {
            list = storage.copyFileContents("data/ChatterBot.txt");
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

    /**
     * This method adds task to the task list, as well as the file chatterbot.txt.
     * @param task This is the task to be added.
     * @param storage This is where the file is accessed.
     * @return String This is the response to the user.
     */
    public String addTask(Task task, Storage storage) {
        assert task != null : "Task to add cannot be null.";
        int initialSize = list.size();
        list.add(task);
        assert list.size() == initialSize + 1 : "Task list size did not increase after adding a task.";
        try {
            storage.appendToFile(task.formatForFile());
        } catch (IOException e) {
            return "Error: File not found.";
        }
        return "Got it. I've added this task:\n" + task.formatForFile() + "\nNow you have " + list.size()
                + " tasks in the list.";
    }

    /**
     * This method deletes tasks from the task list, as well as the file chatterbot.txt.
     * @param taskIndex This is the index to access the task to be deleted.
     * @param storage This is where the file is accessed.
     * @return String This is the response to the user.
     */
    public String deleteTask(int taskIndex, Storage storage) {
        try {
            assert taskIndex >= 0 && taskIndex < list.size() : "Task index must be within list range.";
            int initialSize = list.size();
            if (list.size() == 1) {
                list.clear();
            } else {
                list.remove(taskIndex);
            }
            assert list.size() == initialSize - 1 : "Task list size did not decrease after removing a task.";
        } catch (Exception e) {
            return "Error, task not removed.";
        }
        try {
            storage.writeToFile(convertToString(list));
        } catch (IOException e) {
            return "Error: File not found.";
        }
        return "Noted. I've removed this task:\n" + list.get(taskIndex).formatForFile() + "\nNow you have "
                + list.size() + " tasks in the list.";
    }

    /**
     * This method marks the task as done.
     * @param taskIndex This is the location of the task within the list.
     * @param storage This is where the list is accessed.
     */
    public void setDone(int taskIndex, Storage storage) {
        list.get(taskIndex).setDone();
        try {
            storage.writeToFile(convertToString(list));
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    /**
     * This method marks the task as not done.
     * @param taskIndex This is the location of the task within the list.
     * @param storage This is where the list is accessed.
     */
    public void unsetDone(int taskIndex, Storage storage) {
        list.get(taskIndex).setUndone();
        try {
            storage.writeToFile(convertToString(list));
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    /**
     * This method finds the size of the task list.
     * @return int This is the number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * This method finds the task at taskIndex position in the task list.
     * @param taskIndex This is the position the task is located in the task list.
     * @return Task This is the task at the taskIndex position.
     */
    public Task getTask(int taskIndex) {
        return list.get(taskIndex);
    }

    /**
     * This method finds whether the task description already exists in the list.
     * @param description This is the task description.
     * @return boolean This shows whether the task description is duplicated.
     */
    public boolean isDuplicate(String description) {
        for (int i = 0; i < getSize(); i++) {
            if (getTask(i).contains(description)) {
                return true;
            }
        }
        return false;
    }

}
