package chatterbot.data;

import chatterbot.storage.Storage;
import chatterbot.ui.Ui;

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
        assert task != null : "Task to add cannot be null.";
        int initialSize = list.size();
        list.add(task);
        assert list.size() == initialSize + 1 : "Task list size did not increase after adding a task.";
        try {
            storage.appendToFile(filePath, task.formatForFile());
        } catch (IOException e) {
            return "Error: File not found.";
        }
        return "Got it. I've added this task:\n" + task.formatForFile() + "\nNow you have " + list.size() + " tasks in the list.";
    }

    public String deleteTask(int taskIndex, Storage storage, String filePath) {
        try {
            assert taskIndex >= 0 && taskIndex < list.size() : "Task index must be within list range.";
            int initialSize = list.size();
            list.remove(taskIndex);
            assert list.size() == initialSize - 1 : "Task list size did not decrease after removing a task.";
        } catch (Exception e) {
            return "Error, task not removed.";
        }
        try {
            storage.writeToFile(filePath, convertToString(list));
        } catch (IOException e) {
            return "Error: File not found.";
        }
        return "Noted. I've removed this task:\n" + list.get(taskIndex).formatForFile() + "\nNow you have " + list.size() + " tasks in the list.";
    }

    public String addDeadlineTask(Storage storage, String file, String userMessage, Ui ui, TaskList taskList) {
        int slashDeadline = userMessage.indexOf("/");
        String deadlineDescription = userMessage.substring(9, slashDeadline).trim();
        String deadlineBy = userMessage.substring(slashDeadline + 3).trim();
        Deadline d = new Deadline(deadlineDescription, deadlineBy);
        assert d != null : "Deadline to add cannot be null.";
        list.add(d);
        taskList.addTask(d, storage, file);
        String response = ui.showAddedDeadline(d);
        return response;
    }

    public String addTodoTask(Storage storage, String file, String userMessage, Ui ui, TaskList taskList) {
        Todo td = new Todo(userMessage.substring(5));
        assert td != null : "Todo to add cannot be null.";
        list.add(td);
        taskList.addTask(td, storage, file);
        String response = ui.showAddedTodo(td);
        return response;
    }

    public String addEventTask(Storage storage, String file, String userMessage, Ui ui, TaskList taskList) {
        String[] eventSplit = userMessage.split("/");
        String eventDescription = eventSplit[0].substring(6);
        String eventTo = eventSplit[1].substring(5);
        String eventFrom = eventSplit[2].substring(3);
        Event e = new Event(eventDescription, eventTo, eventFrom);
        assert e != null : "Event to add cannot be null.";
        list.add(e);
        taskList.addTask(e, storage, file);
        String response = ui.showAddedEvent(e);
        return response;
    }

}