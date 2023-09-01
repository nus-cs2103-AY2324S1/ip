package duke;

import task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a list of tasks and provides methods to manage tasks.
 * It can add, delete, mark tasks as done, and provide information about the task list.
 */
public class TaskList {
    private List<Task> todoList;

    /**
     * Constructs a TaskList object, initializing an empty list of tasks.
     */
    public TaskList() {
        this.todoList = new ArrayList<Task>();
    }

    /**
     * Returns the array list of tasks currently stored in the TaskList.
     *
     * @return The array list of tasks.
     */
    public List<Task> getList() {
        return this.todoList;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int getLength() {
        return this.todoList.size();
    }

    /**
     * Retrieves a string representation of a task at the specified index.
     *
     * @param index The index of the task.
     * @return The formatted string representing the task.
     */
    public String getTaskInString(int index) {
        return this.todoList.get(index).toString();
    }

    /**
     * Adds a new task to the task list and prints message
     *
     * @param task The task to be added.
     */
    public void addTask(Task task, Boolean withString) {
        this.todoList.add(task);
        System.out.println("Roger! I have added the following task to the list");
        System.out.println(task.toString());
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.todoList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskToDeleteIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskToDeleteIndex) {
        String taskToRemove = todoList.get(taskToDeleteIndex).toString();
        this.todoList.remove(taskToDeleteIndex);
        System.out.println("Okay! I have removed the following task from the list");
        System.out.println(taskToRemove);
    }

    /**
     * Marks a task as done in the TaskList.
     *
     * @param taskToMarkIndex The index of the task to be marked as done.
     */
    public void markTask(int taskToMarkIndex) {
        this.todoList.get(taskToMarkIndex).markDone();
    }

    /**
     * Marks a task as undone in the TaskList.
     *
     * @param taskToUnmarkIndex The index of the task to be marked as undone.
     */
    public void unmarkTask(int taskToUnmarkIndex) {
        this.todoList.get(taskToUnmarkIndex).markUndone();
    }

    /**
     * Marks a task as undone in the TaskList.
     *
     * @param query The string to be located
     * @return A TaskList object of the matching tasks
     */
    public TaskList findTask(String query) {
        TaskList queryList = new TaskList();
        for (Task t : this.todoList) {
            if (t.compareTitle(query)) {
                queryList.addTask(t);
            } else {
                continue;
            }
        }
        return queryList;
    }


    /**
     * Displays the total number of tasks in the TaskList.
     */
    public void printTaskListInString() {
        System.out.println(String.format("You have %d task(s) currently in the list",
                todoList.size()));
    }

}
