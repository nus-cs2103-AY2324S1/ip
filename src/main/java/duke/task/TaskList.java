package duke.task;

import java.util.ArrayList;

import duke.ui.Ui;

/**
 * Represents a list of tasks and provides methods to manipulate them
 */
public class TaskList {
    private int taskCount;
    private final ArrayList<Task> tasks;

    /**
     * Initializes a dynamic empty task list with a maximum capacity.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
        taskCount = 0;
    }


    /**
     * Checks if the given task index is valid within the task list.
     *
     * @param taskListIndex The index of the task to be checked.
     * @return True if the task index is valid, otherwise false.
     */
    public boolean isValidListIndex(int taskListIndex) {
        return (taskListIndex >= 0 && taskListIndex < taskCount);
    }

    /**
     * Retrieves details of a specific task within the task list.
     *
     * @param taskListIndex The index of the task in the task list.
     * @return Details of the task as a formatted string, or null if the index is invalid.
     */
    public String getTaskDetails(int taskListIndex) {
        if (isValidListIndex(taskListIndex)) {
            Task task = tasks.get(taskListIndex);
            return task.toString();
        } else {
            Ui.showMessage("Invalid Index of task!");
            return null;
        }
    }

    /**
     * Retrieves a specific task within the task list.
     *
     * @param taskListIndex The index of the task in the task list.
     * @return The task, or null if the index is invalid.
     */
    public Task getTask(int taskListIndex) {
        if (isValidListIndex(taskListIndex)) {
            return tasks.get(taskListIndex);
        } else {
            Ui.showMessage("Invalid Index of task!");
            return null;
        }
    }

    /**
     * Gets the current count of tasks in the task list.
     *
     * @return The count of tasks in the task list.
     */
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Adds a task to the task list if space is available.
     *
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.taskCount++;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskListIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskListIndex) {
        this.tasks.remove(taskListIndex);
        this.taskCount--;
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param taskListIndex The index of the task to be marked as done.
     */
    public void markTaskAsDone(int taskListIndex) {
        if (isValidListIndex(taskListIndex)) {
            this.tasks.get(taskListIndex).markAsDone();
        }
    }

    /**
     * Marks a task in the task list as not done.
     *
     * @param taskListIndex The index of the task to be marked as not done.
     */
    public void markTaskAsNotDone(int taskListIndex) {
        if (isValidListIndex(taskListIndex)) {
            this.tasks.get(taskListIndex).markAsNotDone();
        }
    }

    /**
     * Searches for tasks containing the given keyword and returns a list of matching tasks.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Displays the tasks in the task list with their details.
     * example: [T][X] return book
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        if (taskCount == 0) {
            return "Horray!! No tasks in the task list!";
        } else {
            StringBuilder taskListString = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < taskCount; i++) {
                taskListString.append((i + 1)).append(". ").append(this.getTaskDetails(i)).append("\n");
            }
            return taskListString.toString();
        }
    }

    public boolean isEmpty() {
        return (taskCount == 0);
    }
}
