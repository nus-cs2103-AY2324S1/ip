package Models;

import java.util.ArrayList;

import static Database.Dbops.*;
import static Printers.BasicOutputPrinter.printBasicOutput;

/**
 * TaskArray is used to store tasks.
 */
public class TaskArray extends ArrayList<Task> {

    /**
     * Constructor for a new TaskArray.
     */
    public TaskArray() {}

    /**
     * Checks if TaskArray is empty.
     *
     * @return Whether TaskArray is empty
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
     * Adds a task to this TaskArray.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.add(task);
        // Save tasks to file whenever the list changes
        saveTasksToFile(this);
    }

    /**
     * Deletes a task from this TaskArray.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.remove(index);
        // Save tasks to file whenever the list changes
        saveTasksToFile(this);
    }

    /**
     * Marks a task, given its index.
     *
     * @param index The index of the task to be marked.
     */
    public void markTask(int index) {
        this.get(index).markAsDone();
        saveTasksToFile(this);
    }

    /**
     * Unmarks a task, given its index.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        this.get(index).markAsUndone();
        saveTasksToFile(this);
    }

    public void modifyTask(int index, Task modifiedTask) {

    }

    /**
     * Returns a String of all tasks in this TaskArray.
     *
     * @return All tasks in TaskArray.
     */
    @Override
    public String toString() {
        // Display the list of tasks
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < this.size(); i ++) {
            output.append(i + 1).append(". ").append(this.get(i)).append("\n");
        }

        return String.valueOf(output);
    }
}
