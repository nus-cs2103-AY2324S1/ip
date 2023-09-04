package duke;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * The `TaskList` class manages a list of tasks in the Duke application.
 */
public class TaskList {
    private ArrayList<Task> taskArrayList;

    /**
     * Constructs an empty `TaskList`.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Constructs a `TaskList` with an existing ArrayList of tasks.
     *
     * @param taskArrayList The ArrayList containing tasks.
     */
    public TaskList(ArrayList taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.taskArrayList.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        this.taskArrayList.add(task);
    }

    /**
     * Deletes a task at the specified index and returns the deleted task.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        Task x = this.getTask(index);
        this.taskArrayList.remove(index);
        return x;
    }

    /**
     * Gets the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return this.taskArrayList.size();
    }

    /**
     * Converts the task list to a formatted string for display.
     *
     * @return A formatted string representing the tasks in the list.
     */
    public String toString() {
        String botOutput = "";
        for (int i = 1; i <= this.taskArrayList.size(); i++) {
            botOutput = botOutput + i + "." + " " + this.taskArrayList.get(i-1) + "\n    ";
        }
        return botOutput;
    }

    /**
     * Filters and returns a TaskList containing tasks due within one week from the current date.
     *
     * @return A TaskList containing tasks due within one week.
     */
    public TaskList dueWithinWeek() {
        TaskList listWeek = new TaskList();
        for (Task t: this.taskArrayList) {
            LocalDateTime taskDueDate = t.getUrgencyDate();
            LocalDateTime currentDate = LocalDateTime.now();
            long daysDifference = ChronoUnit.DAYS.between(currentDate, taskDueDate);
            // Check if the task's due date is within one week of the current date (7 days)
            if (daysDifference >= 0 && daysDifference <= 7) {
                listWeek.addTask(t);
            }
        }
        return listWeek;
    }

    /**
     * Filters and returns a TaskList containing tasks due within one month from the current date.
     *
     * @return A TaskList containing tasks due within one month.
     */
    public TaskList dueWithinMonth() {
        TaskList listMonth = new TaskList();
        for (Task t: this.taskArrayList) {
            LocalDateTime taskDueDate = t.getUrgencyDate();
            LocalDateTime currentDate = LocalDateTime.now();
            long daysDifference = ChronoUnit.DAYS.between(currentDate, taskDueDate);
            // Check if the task's due date is within one week of the current date (7 days)
            if (daysDifference >= 0 && daysDifference <= 30) {
                listMonth.addTask(t);
            }
        }
        return listMonth;
    }

    public TaskList searchMatches(String queryString) {
        TaskList listSearches = new TaskList();
        for (Task t: this.taskArrayList) {
            String taskDescription = t.getDescription();

            // Check if the task's description contains the queryString
            if (taskDescription.contains(queryString)) {
                listSearches.addTask(t);
            }
        }
        return listSearches;
    }
}
