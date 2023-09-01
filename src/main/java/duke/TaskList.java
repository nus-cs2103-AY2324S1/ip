package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contains the tasks list, it has operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates the task list object.
     *
     * @param tasks The list of tasks to instantiate the task list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a given task into the list.
     *
     * @param task The task to add in.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    //
    // return the deleted task if successfully deleted

    /**
     * Deletes the task at given index of the arraylist of tasks in this instance.
     *
     * @param taskNum The index to remove the task.
     * @return The removed task.
     */
    public Task deleteTask(int taskNum) {
        return this.tasks.remove(taskNum);
    }

    // mark task at index taskNum as done
    // returns false if index out of bounds else true.

    /**
     * Marks the task at a given index as done.
     *
     * @param taskNum The index to mark the task.
     * @return The marked task.
     */
    public Task doTask(int taskNum) {
        Task task = this.tasks.get(taskNum);
        task.doTask();
        return task;
    }

    /**
     * Marks the task at a given index as not done.
     *
     * @param taskNum The index to unmark the task.
     * @return The unmarked task.
     */
    public Task undoTask(int taskNum) {
        Task task = this.tasks.get(taskNum);
        task.undoTask();
        return task;
    }

    /**
     * Returns the number of tasks of the list.
     *
     * @return the number items in the list
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Convert the tasks in the list into the correct format of String.
     *
     * @return Formatted tasks Strings.
     */
    public String saveToStorage() {
        StringBuilder content = new StringBuilder();
        for (Task task : this.tasks) {
            content.append(task.toSaveFormat());
            content.append(System.lineSeparator());
        }
        return content.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append("    " + (i + 1) + ". " + this.tasks.get(i));
            if (i != this.tasks.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    // takes in date input and output the list of tasks on that date (in the form of
    // taskList's toString())
    // No todos will be printed, since there is no dates
    // Events will be printed only if the input date is within the date range of Event
    // Deadline will be printed if the deadline is the same as date input

    /**
     * Finds all the tasks in the list that falls on the given date.
     *
     * @param date The date to check.
     * @return String of all tasks on the date.
     */
    public String findTasksOnDate(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).isOnDate(date)) {
                sb.append("    " + (i + 1) + ". " + this.tasks.get(i));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String find(String description) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).containsDescription(description)) {
                sb.append("    " + (i + 1) + ". " + this.tasks.get(i));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

}
