package jarvis.tasks;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents a list of tasks in the Jarvis application.
 * Provides methods for managing and retrieving tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Initializes a new instance of the TaskList with an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        taskList = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        taskList.add(task);
    }

    /**
     * Sets the tasks in the task list to the provided list of tasks.
     *
     * @param tasks The list of tasks to set in the task list.
     */
    public void setTasks(ArrayList<Task> tasks) {
        taskList.clear();
        taskList.addAll(tasks);
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets the task at the specified index in the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes the task at the specified index in the task list.
     *
     * @return The deleted task.
     */
    public Task deleteTask(int i) {
        Task removedTask = taskList.get(i);
        taskList.remove(i);
        return removedTask;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Sorts the tasks in the task list based on their due dates, with Deadline tasks first,
     * and other tasks at the end of the list.
     *
     * @return An ArrayList of filtered Task based on dueDate.
     */
    public ArrayList<Task> sortTaskByDueDate() {
        ArrayList<Task> deadlineTasks = new ArrayList<>();
        ArrayList<Task> remainingTasks = new ArrayList<>();

        sortDeadlines(deadlineTasks, remainingTasks);
        return deadlineTasks;
    }

    private void sortDeadlines(ArrayList<Task> deadlineTasks, ArrayList<Task> remainingTasks) {
        for (Task task : taskList) {
            if (task instanceof Deadline && task.getDueDateTime() != null) {
                deadlineTasks.add((Task) task);
            } else {
                remainingTasks.add(task);
            }
        }
        deadlineTasks.sort(Comparator.comparing(Task::getDueDateTime));
        deadlineTasks.addAll(remainingTasks);
    }
}
