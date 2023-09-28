package duke.storage;

import java.util.ArrayList;

import duke.helper.Ui;
import duke.tasks.Task;

/**
 * TskList that runs task-related commands and stores task in an arrayList.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
    * constuct the TaskList by initializing the taskList
    */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
    * constuct the TaskList by initializing the taskList
    * with a given arraylist
    *
    * @param tasks the given arraylist
    */
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    /**
    * execute the clear command
    *
    * @return the bot response
    */
    public String clear() {
        taskList.clear();
        Storage.save(taskList);
        return Ui.print("Okay, I have cleared all tasks.");
    }

    /**
    * execute the delete command
    *
    * @param num the index of the task the user want to delete
    * @return the bot response
    */
    public String delete(int num) {
        String taskNumber = "Current # of " + plural(taskList.size() - 1, "task") + ": " + (taskList.size() - 1);
        String[] messageList = {"I've removed this task:", taskList.get(num - 1).getStatus(), taskNumber};
        taskList.remove(num - 1);
        Storage.save(taskList);
        return Ui.print(messageList);
    }

    /**
    * execute the mark command
    *
    * @param num the index of the task the user want to mark
    * @param isDone a boolean value that indicated whether the task is done or not
    * @return the bot response
    */
    public String mark(int num, boolean isDone) {
        String message = taskList.get(num - 1).markItem(isDone);
        Storage.save(taskList);
        return message;
    }

    /**
    * execute find command by printing task with corresponding keyword
    *
    * @param keyword the keyword to search for in the tasks
    * @return the bot response
    */
    public String find(String keyword) {
        int index = 1;
        String message = "";
        for (Task task: taskList) {
            if (task.getTask().contains(keyword)) {
                message = message + (index + ". " + task.getStatus()) + "\n";
                index++;
            }
        }
        index -= 1;
        if (index == 0) {
            return Ui.print("There is no task with keyword: " + keyword);
        } else {
            message = message + ("Current # of " + plural(index, "task") + " with " + keyword + ": " + index);
            return Ui.print(message);
        }
    }

    /**
    * execute print command by printing all tasks
    *
    * @return the bot response
    */
    public String print() {
        int index = 1;
        String message = "";
        for (Task task: taskList) {
            message = message + (index + ". " + task.getStatus()) + "\n";
            index++;
        }
        message = message + ("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size());
        return Ui.print(message);
    }

    /**
    * execute the print command by printing task with given time
    *
    * @param time the time of tasks the user want to see
    * @return the bot response
    */
    public String print(String time) {
        int index = 1;
        String message = "";
        for (Task task: taskList) {
            if (task.getTime() != null && task.getTime().contains(time)) {
                message = message + (index + ". " + task.getStatus()) + "\n";
                index++;
            }
        }
        index -= 1;
        if (index == 0) {
            return Ui.print("There is no task at " + time);
        } else {
            message = message + ("Current # of " + plural(index, "task") + " at " + time + ": " + index);
            return Ui.print(message);
        }
    }

    /**
    * execute the add command
    *
    * @param input the task to add to the taskList
    * @return the bot response
    */
    public String add(Task input) {
        String taskDescription = input.getTask();
        for (Task task: taskList) {
            if (task.getTask().equals(taskDescription)) {
                String duplicateWarning = "Oh no! This task already exists.";
                String[] messageList = {duplicateWarning, task.getStatus(), "Please check your input again."};
                return Ui.print(messageList);
            }
        }
        taskList.add(input);
        Storage.save(taskList);
        String taskNumber = "Current # of " + plural(taskList.size(), "task") + ": " + taskList.size();
        String[] messageList = {("Got it! This task has been added: "), (input.getStatus()), taskNumber};
        return Ui.print(messageList);
    }

    /**
    * dummy test method for add command
    *
    * @param input the task to add to the taskList
    * @return the bot response
    */
    public String addTest(Task input) {
        taskList.add(input);
        String taskNumber = "Current # of " + plural(taskList.size(), "task") + ": " + taskList.size();
        String[] messageList = {("Got it! This task has been added: "), (input.getStatus()), taskNumber};
        return Ui.print(messageList);
    }

    /**
    * returns the size ot the taskList
    *
    * @return the number of tasks in the taskList
    */
    public int size() {
        return taskList.size();
    }

    /**
    * return the task by the given index if it exists
    *
    * @param num the index of the task
    * @return the task by the given index
    */
    public Task get(int num) {
        if (num >= 0 && num < taskList.size()) {
            return taskList.get(num);
        }
        return null;
    }

    private static String plural(int count, String word) {
        return (count <= 1) ? word : (word + "s");
    }
}
