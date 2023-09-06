package ipbot.util;

import ipbot.model.Deadline;
import ipbot.model.Event;
import ipbot.model.Pair;
import ipbot.model.Task;
import ipbot.model.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class to store the list of tasks and act as an interface for interacting with the list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initialises the list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return The size of the list of tasks.
     */
    public int getTasksSize() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added to the list of tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Prints the list of tasks.
     *
     * @param ui The Ui object used to print the tasks.
     */
    public void listTasks(Ui ui) {
        for (int index = 0; index < tasks.size(); index++) {
            ui.printTaskListFormat(tasks.get(index), index + 1);
        }
    }

    /**
     * Prints the deadlines that fall on the date of the given time,
     * and the events that are ongoing at that time.
     *
     * @param ui The Ui object used to print the tasks.
     * @param queryDate The time to query.
     */
    public void listTasks(Ui ui, LocalDateTime queryDate) {
        for (int index = 0; index < tasks.size(); index++) {
            if (queryDate != null) {
                Task currTask = tasks.get(index);
                if (currTask instanceof Event) {
                    Event event = (Event) currTask;
                    if (!event.isOngoing(queryDate)) {
                        continue;
                    }
                } else if (currTask instanceof Deadline) {
                    Deadline deadline = (Deadline) currTask;
                    if (!deadline.isDue(queryDate)) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            ui.printTaskListFormat(tasks.get(index), index + 1);
        }
    }

    public void listTasks(Ui ui, String findStr) {
        if (findStr == null || findStr.isEmpty()) {
            return;
        }
        for (int index = 0; index < tasks.size(); index++) {
            Task currTask = tasks.get(index);
            if (currTask.getDescription().contains(findStr)) {
                ui.printTaskListFormat(tasks.get(index), index + 1);
            }
        }
    }

    /**
     * Marks a task at a given index.
     *
     * @param index The index of the task to mark.
     * @return A Pair, consisting of the marked task, and a boolean representing whether the task was unmarked before.
     */
    public Pair<Task, Boolean> markTask(int index) {
        boolean wasNotMarked = tasks.get(index).markDone();
        return new Pair<>(tasks.get(index), wasNotMarked);
    }

    /**
     * Unmarks a task at a given index.
     *
     * @param index The index of the task to unmark.
     * @return A Pair, consisting of the unmarked task, and a boolean representing whether the task was marked before.
     */
    public Pair<Task, Boolean> unmarkTask(int index) {
        boolean wasNotUnmarked = tasks.get(index).unmarkDone();
        return new Pair<>(tasks.get(index), wasNotUnmarked);
    }

    /**
     * Adds a ToDo object to the list of tasks with the given arguments.
     *
     * @param commandArgs A Map consisting of the arguments for the ToDo object being added.
     * @return The ToDo object added.
     */
    public ToDo addToDoWithArgs(Map<String, String> commandArgs) {
        ToDo toDo = new ToDo(commandArgs.get(""));
        tasks.add(toDo);
        return toDo;
    }

    /**
     * Adds a Deadline object to the list of tasks with the given arguments.
     * Throws an exception for empty arguments or missing "by" argument.
     *
     * @param commandArgs A Map consisting of the arguments for the Deadline object being added.
     * @return The Deadline object added.
     * @throws CommandArgumentException
     */
    public Deadline addDeadlineWithArgs(Map<String, String> commandArgs) throws CommandArgumentException {
        if (commandArgs.get("").isEmpty()) {
            throw new CommandArgumentException("Task description cannot be empty!");
        }
        if (commandArgs.get("by") == null) {
            throw new CommandArgumentException("Deadline missing a /by argument!");
        }
        if (commandArgs.get("by").isEmpty()) {
            throw new CommandArgumentException("/by argument cannot be empty!");
        }
        Deadline deadline = new Deadline(commandArgs.get(""), commandArgs.get("by"));
        tasks.add(deadline);
        return deadline;
    }

    /**
     * Adds an Event object to the list of tasks with the given arguments.
     * Throws an exception for empty arguments or missing "from" or "to" argument.
     *
     * @param commandArgs A Map consisting of the arguments for the Event object being added.
     * @return The Event object added.
     * @throws CommandArgumentException
     */
    public Event addEventWithArgs(Map<String, String> commandArgs) throws CommandArgumentException {
        if (commandArgs.get("").isEmpty()) {
            throw new CommandArgumentException("Task description cannot be empty!");
        }
        if (commandArgs.get("from") == null) {
            throw new CommandArgumentException("Event missing a /from argument!");
        }
        if (commandArgs.get("from").isEmpty()) {
            throw new CommandArgumentException("/from argument cannot be empty!");
        }
        if (commandArgs.get("to") == null) {
            throw new CommandArgumentException("Event missing a /to argument!");
        }
        if (commandArgs.get("to").isEmpty()) {
            throw new CommandArgumentException("/to argument cannot be empty!");
        }
        Event event = new Event(commandArgs.get(""), commandArgs.get("from"), commandArgs.get("to"));
        tasks.add(event);
        return event;
    }

    /**
     * Deletes a task at the given index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        Task task = tasks.remove(index);
        return task;
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            string += tasks.get(i).toCommaString() + "\n";
        }
        return string;
    }
}
