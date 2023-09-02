package taskList;
import java.util.ArrayList;

import task.Task;

/**
 * The `TaskList` class represents a collection of tasks in the BloopBot application.
 * It provides methods to manipulate the task list, such as adding, deleting, marking, and displaying tasks.
 * This class also allows for loading tasks from another `taskList` object.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new `TaskList` object with an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param taskDesc The task to be added.
     */
    public void addTask(Task taskDesc) {
        if (taskList.size() < 100) {
            taskList.add(taskDesc);
        } else {
            System.out.println("100/100 Task limit reached.");
            System.out.println("Pay to upgrade your account.");
        }
    }

    /**
     * Displays the tasks in the task list.
     * If the list is empty, a message indicating no tasks are present is displayed.
     */
    public void displayTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks present in the list");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + "." + taskList.get(i));
            }
        }
    }

    /**
     * Marks a task as completed in the task list.
     *
     * @param taskNum The index of the task to be marked as completed.
     */
    public void doneAndDusted(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            taskList.get(taskNum).isCompleted();
        } else {
            System.out.println("Error: No such Task Number");
        }
    }

    /**
     * Marks a task as not completed in the task list.
     *
     * @param taskNum The index of the task to be marked as not completed.
     */
    public void notDoneNotDusted(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            taskList.get(taskNum).isNotCompleted();
        } else {
            System.out.println("Error: No such Task Number");
        }
    }

    /**
     * Checks if a task is marked as completed.
     *
     * @param taskNum The index of the task to be checked.
     * @return `true` if the task is marked as completed, `false` otherwise.
     */
    public boolean isMarked(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            return taskList.get(taskNum).checkIsDone();
        } else {
            System.out.println("Error: No such Task Number");
            return false;
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNum The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            Task removedTask = taskList.remove(taskNum);
            return removedTask;
        } else {
            System.out.println("Error: No such Task Number");
        }
        return null;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Loads tasks from another `TaskList` object, replacing the current task list.
     *
     * @param loadedTasks The `TaskList` object from which tasks will be loaded.
     */
    public void loadTasks(TaskList loadedTasks) {
        taskList.clear();
        taskList.addAll(loadedTasks.getTasks());
    }
}
