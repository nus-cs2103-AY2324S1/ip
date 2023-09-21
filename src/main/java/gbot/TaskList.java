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
    private final ArrayList<Task> list;
    private int taskCount;

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
            return "No tasks available at the moment as you are amazingly prompt.";
        } else {
            StringBuilder message = new StringBuilder("Here are the tasks in your magnificent list:\n");
            for (int i = 0; i < taskCount; i++) {
                message.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
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
        storage.rewriteFile(list);
        return "Awesome. I've marked this task as done:\n" + curr;
    }

    /**
     * Marks all tasks as done and updates the file.
     */
    public String markAllTasks() {
        for (int i = 0; i < taskCount; i++) {
            list.get(i).markAsDone();
        }
        return "Simply wonderful. All tasks have been marked as done. You're just too good.";
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
        storage.rewriteFile(list);
        return "No worries, this probably isn't important. I've unmarked this task:\n" + curr;
    }

    /**
     * Unmarks all tasks and updates the file.
     */
    public String unmarkAllTasks() {
        for (int i = 0; i < taskCount; i++) {
            list.get(i).markAsUndone();
        }
        storage.rewriteFile(list);
        return "Of course, a busy person needs their time. All tasks have been unmarked.";
    }

    /**
     * Adds a Todo task object to the task list and updates the file.
     *
     * @param str The Todo task description to be added.
     */
    public String addTodo(String str) {
        Todo newTodo = new Todo(str);
        list.add(newTodo);
        storage.writeToFile(newTodo.toStringForFile());
        taskCount++;

        String count = "\nYou now have " + taskCount + " task(s) to do.";
        return "Yet another task to do, you're amazing. Have added this:\n" + newTodo + count;
    }

    /**
     * Adds a Deadline task object to the task list and updates the file.
     *
     * @param desc The Deadline task description to be added.
     * @param deadline The deadline to be added.
     */
    public String addDeadline(String desc, String deadline) {
        if (desc.isBlank() || deadline.isBlank()) {
            return "It appears you may be missing some details, do kindly enter.";
        }

        Deadline newDeadline = new Deadline(desc, deadline);
        list.add(newDeadline);
        storage.writeToFile(newDeadline.toStringForFile());
        taskCount++;

        String count = "\nYou now have " + taskCount + " task(s) to do.";
        return "No rush on this but do take note of the deadline. Have added this:\n" + newDeadline.toString() + count;
    }

    /**
     * Adds an Event task object to the task list and updates the file.
     *
     * @param desc The Event task description to be added.
     * @param fromDate The start date of the event.
     * @param toDate The end date of the event.
     */
    public String addEvent(String desc, String fromDate, String toDate) {
        if (desc.isBlank() || fromDate.isBlank() || toDate.isBlank()) {
            return "It appears you may be missing some details, do kindly enter.";
        }
        Event newEvent = new Event(desc, fromDate, toDate);
        list.add(newEvent);
        storage.writeToFile(newEvent.toStringForFile());
        taskCount++;

        String count = "\nYou now have " + taskCount + " task(s) to do.";
        return "Events are the norm of the upper echelon. Have added this event:\n" + newEvent + count;
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
        storage.rewriteFile(list);
        taskCount--;

        String count = "\nYou now have " + taskCount + " task(s) to do.";
        return "Just clearing up I see. I got you, I've removed this task:\n" + toDelete + count;
    }

    /**
     * Deletes all tasks and updates the file.
     */
    public String deleteAllTasks() {
        list.clear();
        storage.rewriteFile(list);
        taskCount = 0;
        return "Already done? Amazing. I've removed all existing tasks.\n";
    }

    /**
     * Finds and prints matching tasks in the task list with the given keyword.
     *
     * @param keyword The keyword given by user.
     */
    public String find(String keyword) {
        ArrayList<Task> matchList = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            Task curr = list.get(i);
            if (curr.getDescription().toLowerCase().contains(keyword)) {
                matchList.add(curr);
            }
        }

        if (matchList.isEmpty()) {
            return "I must apologise. There are no matching tasks.";
        } else {
            StringBuilder message = new StringBuilder("Here are the matching tasks in your beautiful list:\n");
            for (int i = 0; i < matchList.size(); i++) {
                message.append(i + 1).append(". ").append(matchList.get(i).toString()).append("\n");
            }
            return message.toString();
        }
    }
}
