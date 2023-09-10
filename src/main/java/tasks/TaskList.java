package tasks;

import java.util.ArrayList;
import java.util.Arrays;

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
        return String.format(
                "Got it. I've added this task:\n"
                        + "     %s\n"
                        + "%s",
                task, getTaskCountMessage());
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
        return String.format("Ok! I've marked this task as done:\n"
                        + "     %s\n"
                        + "%s",
                task, getTaskCountMessage());
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
        return String.format("Ok! I've marked this task as undone:\n"
                        + "     %s\n"
                        + "%s\n",
                task, getTaskCountMessage());
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
        return String.format("Ok! I've deleted this task:\n "
                        + "     %s\n"
                        + "%s",
                task, getTaskCountMessage());
    }

    /**
     * Lists all tasks in the list and returns them along with task numbers as a formatted string.
     *
     * @return A formatted string containing the list of tasks with task numbers.
     */
    public String listAllTasks() {
        StringBuilder taskListBuilder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= this.tasks.size(); ++i) {
            String taskNumber = String.format("%3d.", i);
            taskListBuilder.append(String.format("%s %s\n", taskNumber, this.tasks.get(i - 1).toString()));
        }
        taskListBuilder.append(getTaskCountMessage());
        return taskListBuilder.toString();
    }

    /**
     * Finds tasks that contain a specified keyword in their descriptions and returns a formatted string.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A formatted string containing the matching tasks or a message if there are no matches.
     */
    public String findTask(String keyword) {
        StringBuilder matchingTasksBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        boolean hasMatch = false;

        for (int i = 1; i <= this.tasks.size(); ++i) {
            Task currTask = this.tasks.get(i - 1);
            if (currTask.hasKeyWord(keyword)) {
                hasMatch = true;
                String taskNumber = String.format("%3d.", i);
                matchingTasksBuilder.append(String.format("%s %s\n", taskNumber, currTask));
            }
        }

        if (!hasMatch) {
            matchingTasksBuilder.append("No tasks matched your keyword!\n");
        }

        matchingTasksBuilder.append(getTaskCountMessage());
        return matchingTasksBuilder.toString();
    }

    /**
     * Returns a string indicating the number of tasks in the task list.
     *
     * @return A string mentioning the number of tasks in the task list.
     */
    public String getTaskCountMessage() {
        return String.format("You have %d tasks in the task list.\n", this.size());
    }

    /**
     * Validates whether a task index is valid within the list.
     *
     * @param text The text representing the task index to validate.
     * @return An empty string if the task index is valid, or an error message if it's invalid.
     */
    public String validateTaskIndex(String text) {
        int index;
        try {
            index = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return String.format("Task index: '%s' is invalid, task index has to be in integer format.\n", text);
        } catch (Exception e) {
            return String.format("Error while parsing task index: '%s'.\n", text);
        }

        if (index < 1 || index > this.tasks.size()) {
            return String.format("Task index: '%s' is invalid, task index has to be in the list.\n", text);
        }

        return ""; // Return an empty string if the task index is valid
    }

    /**
     * Checks if there is an error based on the validation result.
     *
     * @param validationError The validation error message.
     * @return True if there is an error (validationError is not empty), false otherwise.
     */
    public boolean isValidationError(String validationError) {
        return !validationError.isEmpty();
    }
}
