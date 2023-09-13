package duke;

import java.util.ArrayList;
import tasks.Task;

/**
 * The Duke.TaskList class represents a list of tasks and provides various methods for managing and interacting with those tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a new Duke.TaskList with the provided list of tasks.
     *
     * @param tasks The list of tasks to initialize the Duke.TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addToList(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a task as done by its index in the list.
     *
     * @param taskNum The index of the task to mark as done.
     */
    public void markDone(int taskNum) {
        this.tasks.get(taskNum).markDone();
    }

    /**
     * Marks a task as undone by its index in the list.
     *
     * @param taskNum The index of the task to mark as undone.
     */
    public void markUndone(int taskNum) {
        this.tasks.get(taskNum).markUndone();
    }

    /**
     * Returns the string representation of a task by its index.
     *
     * @param taskNum The index of the task to print.
     * @return A string representation of the task.
     */
    public String printTask(int taskNum) {
        return this.tasks.get(taskNum).toString();
    }

    /**
     * Returns the name of a task by its index.
     *
     * @param taskNum The index of the task to print the name of.
     * @return The name of the task.
     */
    public String printName(int taskNum) {
        return this.tasks.get(taskNum).getName();
    }

    /**
     * Prints the number of tasks in the list.
     */
    public String numOfTask() {
        return "N... Now you have... " + this.tasks.size() + " tasks in the list. o(>ω<)o\n";
    }

    /**
     * Deletes a task by its index and prints its details before removal.
     *
     * @param taskNum The index of the task to delete.
     */
    public void deleteTask(int taskNum) {
        System.out.println(this.printTask(taskNum));
        this.tasks.remove(taskNum);
    }
    public int totalTaskNum() {
        return this.tasks.size();
    }

    /**
     * Prints the entire list of tasks with numbering for reference.
     */
    public String printList() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            int x = i + 1;
            result += x + ". " + this.printTask(i) + "\n";
        }
        return result;
    }
}
