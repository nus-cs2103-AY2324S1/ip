package gbot;

import exceptions.TaskException;
import java.util.ArrayList;
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
    private ArrayList<Task> list;
    private int taskCount;
    private static final String LINE = "____________________________________________________________";

    /**
     * Initialises a TaskList objects that loads tasks from a given Storage object.
     *
     * @param storage The Storage object to load tasks from.
     */

    public TaskList(Storage storage) {
        this.storage = storage;
        this.list = storage.load();
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
                message.append((i + 1) + ". " + list.get(i).toString() + "\n");
            }
            return message.toString();
        }
    }

    /**
     * Marks the corresponding task as done and updates the file.
     *
     * @param taskNum The task number provided by the user.
     */
    public String markTask(int taskNum) {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new TaskException();
        }

        Task curr = list.get(taskNum - 1);
        curr.markAsDone();
        storage.updateFile(list);
        return "Nice, I've marked this task as done:\n" + curr;
    }

    /**
     * Unmarks the corresponding task as not done and updates the file.
     *
     * @param taskNum The task number provided by the user.
     */
    public String unmarkTask(int taskNum) {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new TaskException();
        }

        Task curr = list.get(taskNum - 1);
        curr.markAsUndone();
        storage.updateFile(list);
        return "Okay, I've unmarked this task:\n" + curr;
    }

    /**
     * Adds a Todo task object to the task list and updates the file.
     *
     * @param str The Todo task description to be added.
     */
    public String addTodo(String str) {
        Todo newTodo = new Todo(str);
        list.add(newTodo);
        storage.appendFile(newTodo.toStringForFile());
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
        String desc = str.split(" /by ")[0];
        String by = str.split(" /by ")[1];
        Deadline newDeadline = new Deadline(desc, by);
        list.add(newDeadline);
        storage.appendFile(newDeadline.toStringForFile());
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
        String desc = str.split(" /from ")[0];
        String from = str.split(" /from ")[1].split(" /to ")[0];
        String to = str.split(" /from ")[1].split(" /to ")[1];
        Event newEvent = new Event(desc, from, to);
        list.add(newEvent);
        storage.appendFile(newEvent.toStringForFile());
        taskCount++;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        return "Got it. I've added this task:\n" + newEvent + count;
    }

    /**
     * Deletes the corresponding task from the task list and updates the file.
     *
     * @param taskNum The task number provided by the user.
     */
    public String deleteTask(int taskNum) {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new TaskException();
        }

        Task toDelete = list.get(taskNum - 1);
        list.remove(taskNum - 1);
        storage.updateFile(list);
        taskCount--;

        String count = "\nNow you have " + taskCount + " tasks to do.";
        return "Noted. I've removed this task:\n" + toDelete + count;
    }

    /**
     * Finds and prints matching tasks in the task list with the given keyword.
     *
     * @param str The keyword given by user.
     */
    public String find(String str) {
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
                message.append((i + 1) + ". " + matchList.get(i).toString() + "\n");
            }
            return message.toString();
        }
    }
}
