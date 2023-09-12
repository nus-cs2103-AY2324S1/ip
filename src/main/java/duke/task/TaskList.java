package duke.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.exception.DukeException;

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
    }


    /**
     * Checks if the given task index is valid within the task list.
     *
     * @param taskListIndex The index of the task to be checked.
     * @return True if the task index is valid, otherwise false.
     */
    public boolean isValidListIndex(int taskListIndex) {
        return (taskListIndex >= 0 && taskListIndex < tasks.size());
    }

    /**
     * Retrieves details of a specific task within the task list.
     *
     * @param taskListIndex The index of the task in the task list.
     * @return Details of the task as a formatted string, or null if the index is invalid.
     * @throws DukeException If the index is invalid.
     */
    public String getTaskDetails(int taskListIndex) throws DukeException {
        if (isValidListIndex(taskListIndex)) {
            Task task = tasks.get(taskListIndex);
            return task.toString();
        } else {
            throw new DukeException("Invalid Index");
        }
    }

    /**
     * Retrieves a specific task within the task list.
     *
     * @param taskListIndex The index of the task in the task list.
     * @return The task, or null if the index is invalid.
     * @throws DukeException If the index is invalid.
     */
    public Task getTask(int taskListIndex) throws DukeException {
        if (isValidListIndex(taskListIndex)) {
            return tasks.get(taskListIndex);
        } else {
            throw new DukeException("Invalid Index");
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
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
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
            String taskListString = tasks.stream()
                    .map(Task::toString)
                    .collect(Collectors.joining("\n", "Here are the tasks in your list:\n", ""));
            return taskListString;
        }
    }

    public boolean isEmpty() {
        return (taskCount == 0);
    }
}
