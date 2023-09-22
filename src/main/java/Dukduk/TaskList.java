package dukduk;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

/**
 * Represents a list of tasks and provides methods to manage them.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Sets the list of tasks in the TaskList.
     *
     * @param tasks An ArrayList of tasks to set as the tasks in the TaskList.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks in the TaskList.
     *
     * @return An ArrayList of tasks stored in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the count of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Checks if a given task index is valid.
     *
     * @param taskIndex The index of the task to check.
     * @return True if the task index is valid, false otherwise.
     */
    public boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add to the TaskList.
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    public void markTaskAsDone(int taskIndex) {
        assert isValidTaskIndex(taskIndex) : "Invalid task index";
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).markAsDone();
        }
    }

    /**
     * Unmarks a task at the specified index as not done.
     *
     * @param taskIndex The index of the task to unmark.
     */
    public void unMarkTask(int taskIndex) {
        assert isValidTaskIndex(taskIndex) : "Invalid task index";
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).unmark();
        }
    }

    /**
     * Deletes a task at the specified index from the TaskList.
     *
     * @param taskIndex The index of the task to delete.
     */
    public String deleteTask(int taskIndex) {
        assert isValidTaskIndex(taskIndex) : "Invalid task index";
        Task removedTask = tasks.remove(taskIndex);
        return String.format("Quack, noted. I've removed this task:\n%s\n" +
                "Now you have %d tasks in the list.", removedTask, tasks.size());
    }

    /**
     * Finds a task by searching for a keyword.
     * Has been edited for extension C-BetterSearch.
     * Allows for partial word matching, case-insensitive matching and searching for date/time.
     *
     * @param keyword Used for searching tasks that matches keyword.
     * @return An ArrayList of tasks that match the keyword partially.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null : "Keyword cannot be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lowerKeyword)) {
                matchingTasks.add(task);
            }
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                String deadlineString = deadlineTask.getBy().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                if (deadlineString.contains(lowerKeyword)) {
                    matchingTasks.add(task);
                }
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                String fromString = eventTask.getFrom().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                String toString = eventTask.getTo().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                if (fromString.contains(lowerKeyword) || toString.contains(lowerKeyword)) {
                    matchingTasks.add(task);
                }
            }
        }
        return matchingTasks;
    }
}

