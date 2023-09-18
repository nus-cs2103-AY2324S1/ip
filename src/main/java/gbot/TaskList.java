package gbot;

import exceptions.DeadlineException;
import exceptions.EventException;
import exceptions.TaskException;
import java.util.ArrayList;

import exceptions.TodoException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * The class that contains all task operations for the chatbot.
 *
 * @author Gallen Ong
 */
public class TaskList {
    private Storage storage;
    private final ArrayList<Task> list;
    private int taskCount;
    private static final String INVALIDTASK = "Please enter a task number.";

    /**
     * Initialises a TaskList objects that loads tasks from a given Storage object.
     *
     * @param storage The Storage object to load tasks from.
     */

    public TaskList(Storage storage) {
        this.storage = storage;
        this.list = storage.loadFile();
        this.taskCount = this.list.size();
    }

    /**
     * Empty constructor for testing purposes.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns the list of existing tasks in the TaskList.
     */
    public String listTasks() {
        if (taskCount == 0) {
            return "No tasks available.";
        } else {
            StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < taskCount; i++) {
                message.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
            }
            return message.toString();
        }
    }

    /**
     * Marks the corresponding task as done and updates the file.
     *
     * @param str The task number provided by the user.
     */
    public String markTask(String str) {
        if (str.isBlank()) {
            return INVALIDTASK;
        }

        int taskNum;
        try {
            taskNum = Integer.parseInt(str.split(" ")[0]);
        } catch (NumberFormatException e) {
            return INVALIDTASK;
        }

        if (taskNum > list.size() || taskNum <= 0) {
            throw new TaskException();
        }

        Task curr = list.get(taskNum - 1);
        curr.markAsDone();
        storage.rewriteFile(list);
        return "Nice. I've marked this task as done:\n" + curr;
    }

    public String markAllTasks() {
        for (int i = 0; i < taskCount; i++) {
            list.get(i).markAsDone();
        }
        return "All tasks marked as done!";
    }

    /**
     * Unmarks the corresponding task as not done and updates the file.
     *
     * @param str The task number provided by the user.
     */
    public String unmarkTask(String str) {
        if (str.isBlank()) {
            return INVALIDTASK;
        }

        int taskNum;
        try {
            taskNum = Integer.parseInt(str.split(" ")[0]);
        } catch (NumberFormatException e) {
            return INVALIDTASK;
        }

        if (taskNum > list.size() || taskNum <= 0) {
            throw new TaskException();
        }

        Task curr = list.get(taskNum - 1);
        curr.markAsUndone();
        storage.rewriteFile(list);
        return "Okay, I've unmarked this task:\n" + curr;
    }

    public String unmarkAllTasks() {
        for (int i = 0; i < taskCount; i++) {
            list.get(i).markAsUndone();
        }
        return "All tasks unmarked!";
    }

    /**
     * Adds a Todo task object to the task list and updates the file.
     *
     * @param str The Todo task description to be added.
     */
    public String addTodo(String str) {
        if (str.isBlank()) {
            throw new TodoException();
        }

        Todo newTodo = new Todo(str);
        list.add(newTodo);
        storage.writeToFile(newTodo.toStringForFile());
        taskCount++;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        return "Got it. I've added this task:\n" + newTodo + count;
    }

    /**
     * Adds a Deadline task object to the task list and updates the file.
     *
     * @param str The Deadline task description to be added.
     */
    public String addDeadline(String str) {
        if (str.isBlank()) {
            throw new DeadlineException();
        }

        String desc = str.split(" /by ")[0];
        String by = str.split(" /by ")[1];
        Deadline newDeadline = new Deadline(desc, by);
        list.add(newDeadline);
        storage.writeToFile(newDeadline.toStringForFile());
        taskCount++;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        return "Got it. I've added this task:\n" + newDeadline.toString() + count;
    }

    /**
     * Adds an Event task object to the task list and updates the file.
     *
     * @param str The Event task description to be added.
     */
    public String addEvent(String str) {
        if (str.isBlank()) {
            throw new EventException();
        }

        String desc = str.split(" /from ")[0];
        String from = str.split(" /from ")[1].split(" /to ")[0];
        String to = str.split(" /from ")[1].split(" /to ")[1];
        Event newEvent = new Event(desc, from, to);
        list.add(newEvent);
        storage.writeToFile(newEvent.toStringForFile());
        taskCount++;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        return "Got it. I've added this task:\n" + newEvent + count;
    }

    /**
     * Deletes the corresponding task from the task list and updates the file.
     *
     * @param str The task number provided by the user.
     */
    public String deleteTask(String str) {
        if (str.isBlank()) {
            return INVALIDTASK;
        }

        int taskNum;
        try {
            taskNum = Integer.parseInt(str.split(" ")[0]);
        } catch (NumberFormatException e) {
            return INVALIDTASK;
        }

        if (taskNum > list.size() || taskNum <= 0) {
            throw new TaskException();
        }

        Task toDelete = list.get(taskNum - 1);
        list.remove(taskNum - 1);
        storage.rewriteFile(list);
        taskCount--;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        return "Noted. I've removed this task:\n" + toDelete + count;
    }

    public String deleteAllTasks() {
        list.clear();
        storage.rewriteFile(list);
        taskCount = 0;
        return "Noted. I've removed all present tasks:\n";
    }

    /**
     * Finds and prints matching tasks in the task list with the given keyword.
     *
     * @param str The keyword given by user.
     */
    public String find(String str) {
        if (str.isBlank()) {
            return "Please enter a keyword.";
        }

        ArrayList<Task> matchList = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            Task curr = list.get(i);
            if (curr.getDescription().toLowerCase().contains(str)) {
                matchList.add(curr);
            }
        }

        if (matchList.isEmpty()) {
            return "Sorry. There are no matching tasks.";
        } else {
            StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchList.size(); i++) {
                message.append(i + 1).append(". ").append(matchList.get(i).toString()).append("\n");
            }
            return message.toString();
        }
    }
}
