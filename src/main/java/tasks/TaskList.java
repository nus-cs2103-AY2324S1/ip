package tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import enums.ExceptionMessage;
import enums.WoofMessage;
import exceptions.WoofInvalidCommandException;

/**
 * The `TaskList` class represents a collection of tasks in the Woof application.
 * It provides methods for adding, marking tasks as done/undone, deleting tasks,
 * and listing all tasks in the list.
 */
public class TaskList {
    /**
     * The list of tasks managed by this `TaskList`.
     */
    protected ArrayList<Task> tasks;

    /**
     * Constructs a `TaskList` with an initial array of tasks.
     *
     * @param tasks The initial array of tasks to populate the list.
     */
    public TaskList(Task[] tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>(1);
        } else {
            this.tasks = new ArrayList<>(Arrays.asList(tasks));
        }
    }

    /**
     * Gets a task from the list by its index.
     *
     * @param taskIndex The index of the task to retrieve.
     * @return The task at the specified index, or `null` if the index is out of bounds.
     */
    public Task getTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            return null;
        }
        return this.tasks.get(taskIndex);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves all tasks in the list as an array.
     *
     * @return An array containing all the tasks in the list.
     */
    public Task[] getTasks() {
        Task[] taskArray = new Task[this.size()];
        return this.tasks.toArray(taskArray);
    }

    /**
     * Adds a task to the list and returns a confirmation message.
     *
     * @param task The task to be added.
     * @return A confirmation message.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return WoofMessage.TASK_ADDED.toFormattedString(task.getTabStopOne(), task.toString(), getTaskCountMessage());
    }

    /**
     * Marks a task as done and returns a confirmation message.
     *
     * @param text The text representing the task to be marked as done.
     * @return A confirmation message.
     */
    public String markTaskDone(String text) {
        int taskIndex = Integer.parseInt(text) - 1;
        Task task = this.tasks.get(taskIndex);
        task.markDone();
        return WoofMessage.TASK_DONE.toFormattedString(task.getTabStopOne(), task.toString(), getTaskCountMessage());
    }

    /**
     * Marks a task as undone and returns a confirmation message.
     *
     * @param text The text representing the task to be marked as undone.
     * @return A confirmation message.
     */
    public String markTaskUndone(String text) {
        int taskIndex = Integer.parseInt(text) - 1;
        Task task = this.tasks.get(taskIndex);
        task.markUndone();
        return WoofMessage.TASK_UNDONE.toFormattedString(task.getTabStopOne(), task.toString(), getTaskCountMessage());
    }

    /**
     * Deletes a task from the list and returns a confirmation message.
     *
     * @param text The text representing the task to be deleted.
     * @return A confirmation message.
     */
    public String deleteTask(String text) {
        int taskIndex = Integer.parseInt(text) - 1;
        Task task = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        return WoofMessage.TASK_DELETED.toFormattedString(task.getTabStopOne(), task.toString(), getTaskCountMessage());
    }

    /**
     * Lists all tasks in the list and returns them along with task numbers as a formatted string.
     *
     * @return A formatted string containing the list of tasks with task numbers.
     */
    public String listAllTasks() {
        StringBuilder taskListBuilder = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); ++i) {
            String taskNumber = String.format("%3d.", i);
            taskListBuilder.append(String.format("%s %s", taskNumber, this.tasks.get(i - 1).toString()));
        }
        return WoofMessage.TASKS_LISTED.toFormattedString(taskListBuilder.toString(), getTaskCountMessage());
    }

    /**
     * Sorts the tasks in the task list based on their natural order (using compareTo).
     *
     * @return A message indicating the sorting result or an error message if the task list is empty.
     */
    public String sortTasks() {
        StringBuilder sortedTasks = new StringBuilder();
        Collections.sort(this.tasks);
        for (int i = 1; i <= this.tasks.size(); ++i) {
            String taskNumber = String.format("%3d.", i);
            sortedTasks.append(String.format("%s %s", taskNumber, this.tasks.get(i - 1).toString()));
        }

        if (this.size() == 0) {
            return WoofMessage.TASKS_SORTED.toFormattedString(
                WoofMessage.EMPTY_TASK_LIST.toFormattedString(),
                getTaskCountMessage());
        }

        return WoofMessage.TASKS_SORTED.toFormattedString(sortedTasks.toString(), getTaskCountMessage());
    }

    /**
     * Searches for tasks that contain any of the specified keywords in their descriptions.
     *
     * @param keywords The keywords to search for within task descriptions.
     * @return A string containing the matching tasks or a message if no matches are found.
     */
    public String findTask(String ...keywords) {
        StringBuilder matchedTasks = new StringBuilder();
        boolean hasMatch = false;

        for (int i = 1; i <= this.tasks.size(); ++i) {
            Task currTask = this.tasks.get(i - 1);
            if (containsKeywords(currTask, keywords)) {
                hasMatch = true;
                String taskNumber = String.format("%3d.", i);
                matchedTasks.append(String.format("%s %s", taskNumber, currTask));
            }
        }

        if (!hasMatch) {
            return WoofMessage.TASKS_FOUND.toFormattedString(
                WoofMessage.NO_MATCHING_TASKS.toFormattedString(),
                getTaskCountMessage());
        }

        return WoofMessage.TASKS_FOUND.toFormattedString(matchedTasks.toString(), getTaskCountMessage());
    }

    /**
     * Checks if a task contains any of the specified keywords within its description.
     *
     * @param task     The task to check for keyword matches.
     * @param keywords The keywords to search for within the task's description.
     * @return true if the task contains any of the specified keywords, false otherwise.
     */
    private boolean containsKeywords(Task task, String[] keywords) {
        for (String keyword : keywords) {
            if (task.hasKeyWord(keyword)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns a string indicating the number of tasks in the task list.
     *
     * @return A string mentioning the number of tasks in the task list.
     */
    public String getTaskCountMessage() {
        return WoofMessage.TASK_LIST_COUNT.toFormattedString(String.valueOf(this.size()));
    }

    /**
     * Validates whether a task index is valid within the list.
     *
     * @param text The text representing the task index to validate.
     */
    public static void validateTaskIndex(String text, TaskList taskList) {
        int index;
        try {
            index = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_TASK_INDEX.toFormattedString(text)
            );
        } catch (Exception e) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.UNABLE_TO_PARSE_INDEX.toFormattedString(text, e.getMessage())
            );
        }
        if (index < 1 || index > taskList.size()) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.TASK_INDEX_NOT_IN_LIST.toFormattedString(text)
            );
        }
    }
}
