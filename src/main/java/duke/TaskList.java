package duke;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Task;


/**
 * The `TaskList` class manages a list of tasks in the Duke application.
 */
public class TaskList {
    public static final int DAYS_IN_A_WEEK = 7;
    public static final int DAYS_IN_A_MONTH = 30;
    public ArrayList<Task> taskArrayList;

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
        String botOutput = IntStream.range(0, this.taskArrayList.size())
                .mapToObj(i -> (i + 1) + ". " + this.taskArrayList.get(i))
                .collect(Collectors.joining("\n    "));
        return botOutput;
    }

    /**
     * Filters and returns a TaskList containing tasks due within one week from the current date.
     *
     * @return A TaskList containing tasks due within one week.
     */
    public TaskList dueWithinWeek() {
        TaskList listWeek = new TaskList();
        LocalDateTime currentDate = LocalDateTime.now();

        listWeek.taskArrayList = this.taskArrayList.stream().filter(t -> {
            LocalDateTime taskDueDate = t.getUrgencyDate();
            long daysDifference = ChronoUnit.DAYS.between(currentDate, taskDueDate);
            return daysDifference >= 0 && daysDifference <= DAYS_IN_A_WEEK;
        }).collect(Collectors.toCollection(ArrayList:: new));

        return listWeek;
    }

    /**
     * Filters and returns a TaskList containing tasks due within one month from the current date.
     *
     * @return A TaskList containing tasks due within one month.
     */
    public TaskList dueWithinMonth() {
        TaskList listMonth = new TaskList();
        LocalDateTime currentDate = LocalDateTime.now();

        listMonth.taskArrayList = this.taskArrayList.stream().filter(t -> {
            LocalDateTime taskDueDate = t.getUrgencyDate();
            long daysDifference = ChronoUnit.DAYS.between(currentDate, taskDueDate);
            return daysDifference >= 0 && daysDifference <= DAYS_IN_A_MONTH;
        }).collect(Collectors.toCollection(ArrayList:: new));

        return listMonth;
    }

    /**
     * Searches for relevant or matching tasks within the tasklist.
     *
     * @param queryStrings The user's input query string.
     * @return The TaskList of matching tasks.
     */
    public TaskList searchMatches(String... queryStrings) {
        TaskList listSearches = new TaskList();
        for (Task t: this.taskArrayList) {
            String taskDescription = t.getDescription();
            searchMatchesInTask(listSearches, t, taskDescription, queryStrings);
        }
        return listSearches;
    }

    private static void searchMatchesInTask(TaskList listSearches, Task t, String taskDescription, String[] queryStrings) {
        for (String queryString : queryStrings) {
            if (taskDescription.contains(queryString)) {
                listSearches.addTask(t);
                break; //Break the loop if a match is found with any one keyword.
            }
        }
    }
}
