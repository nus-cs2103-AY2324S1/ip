package duke;

import Exceptions.DukeArgumentException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class encapsulates the current tasks.
 */
public class TaskList {
    protected static ArrayList<Task> taskArray;
    protected static Storage storage;
    protected static int numTask;
    protected static Parser parser;
    /**
     * Constructor method for the duke.TaskList class.
     * @param loadTask The list of tasks to be used.
     * @param store The place where the tasks are stored.
     */
    public TaskList(ArrayList<Task> loadTask, Storage store) {
        taskArray = loadTask;
        numTask = taskArray.size();
        storage = store;
        parser = new Parser();
    }
    /**
     * Empty constructor method for the duke.TaskList class.
     */
    public TaskList() {
        taskArray = new ArrayList<>();
        parser = new Parser();
    }
    /**
     * Marks a provided task as not done.
     * @param i The information of the task to be unmarked.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided task number is invalid.
     */
    public String unmark(String i) throws DukeArgumentException {
        int taskId = Integer.parseInt(i.substring(7)) - 1;
        String message;
        if (taskId < 0 || taskId >= numTask) {
            throw new DukeArgumentException("      I think you keyed in the wrong task number..");
        }
        message = taskArray.get(taskId).markAsUndone();
        // update the duke.txt
        try {
            storage.saveTask(taskArray);
        } catch (IOException e) {
            return "      Uhm.. something is not working right..";
        }
        return message;
    }
    /**
     * Marks a provided task as done.
     * @param i The information of the task to be marked done.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided task number is invalid.
     */
    public String mark(String i) throws DukeArgumentException {
        int taskId = Integer.parseInt(i.substring(5)) - 1;
        String message;
        if (taskId < 0 || taskId >= numTask) {
            throw new DukeArgumentException("      I think you keyed in the wrong task number..");
        }
        message = taskArray.get(taskId).markAsDone();
        // update the duke.txt
        try {
            storage.saveTask(taskArray);
        } catch (IOException e) {
            return "      Uhm.. something is not working right..";
        }
        return message;
    }
    /**
     * Deletes a task from the task list.
     * @param i The information of the task to be deleted.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided task number is invalid.
     */
    public static String deleteTask(String i) throws DukeArgumentException {
        int deleteTask = Integer.parseInt(i.substring(7)) - 1;
        if (deleteTask < 0 || deleteTask >= numTask) {
            throw new DukeArgumentException("      I think you keyed in the wrong task number..");
        }
        Task removed = taskArray.get(deleteTask);
        taskArray.remove(deleteTask);
        numTask--;
        return "     Noted. I've removed this task:\n"
                + "     " + removed.printDesc() + "\n"
                + "     Now you have " + numTask + " tasks in the list.";
    }
    /**
     * Retrieves the full task list.
     * @return The full task list.
     */
    public static String listTask() {
        String message;
        message = "     Here are the tasks in your list:\n";
        for (int a = 0; a < numTask; a++) {
            message += "     " + (a + 1) + ". " + taskArray.get(a).printDesc() + "\n";
        }
        return message;
    }
    /**
     * Adds a Todo task to the task list.
     * @param i The information of the Todo task to be added.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided information is invalid.
     */
    public static String todoTask(String i) throws DukeArgumentException {
        String[] taskDetails = parser.commandSplit(i);
        String message;
        if (taskDetails.length != 2) {
            throw new DukeArgumentException("     OOPS!!! The description of todo cannot be empty :(");
        }
        taskArray.add(new Todo(taskDetails[1]));
        message = taskArray.get(numTask).printMessage(numTask);
        numTask++;
        return message;
    }
    /**
     * Adds a Deadline task to the task list.
     * @param i The information of the Deadline task to be added.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided information is invalid.
     */
    public static String deadlineTask(String i) throws DukeArgumentException {
        String[] taskDetails = parser.commandSplit(i);
        String message;
        if (taskDetails.length != 2) {
            throw new DukeArgumentException("     OOPS!!! The description of deadline cannot be empty :(");
        }
        String[] deadlineDetails = parser.deadlineDetails(taskDetails[1]);
        if (deadlineDetails.length != 2) {
            throw new DukeArgumentException("     OOPS!!! Where is the deadline time?");
        }
        taskArray.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
        message = taskArray.get(numTask).printMessage(numTask);
        numTask++;
        return message;
    }
    /**
     * Adds a Event task to the task list.
     * @param i The information of the Event task to be added.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided information is invalid.
     */
    public static String eventTask(String i) throws DukeArgumentException {
        String[] taskDetails = parser.commandSplit(i);
        String message;
        if (taskDetails.length != 2) {
            throw new DukeArgumentException("     OOPS!!! The description of event cannot be empty :(");
        }
        String[] eventDetails = parser.eventDetails(taskDetails[1]);
        if (eventDetails.length != 3) {
            throw new DukeArgumentException("     OOPS!!! The details for the event is missing!");
        }
        taskArray.add(new Event(eventDetails[0], eventDetails[1].substring(5),
                eventDetails[2].substring(3)));
        message = taskArray.get(numTask).printMessage(numTask);
        numTask++;
        return message;
    }
    protected ArrayList<Task> getTaskArray() {
        return taskArray;
    }
    protected String findTask(String info) {
        String keyword = (parser.commandSplit(info))[1];
        String message;
        ArrayList<Task> keywordTasks = new ArrayList<>();
        for (int i = 0; i < numTask; i++) {
            if (taskArray.get(i).getDesc().contains(keyword)) {
                keywordTasks.add(taskArray.get(i));
            }
        }
        message = "     Here are the matching tasks in your list:\n";
        for (int a = 0; a < keywordTasks.size(); a++) {
            message += "     " + (a + 1) + ". " + keywordTasks.get(a).printDesc() + "\n";
        }
        return message;
    }
}
