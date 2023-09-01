package task;

import java.util.ArrayList;

/**
 * Represents a list of task to do
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * returns the list of tasks
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * returns the length of to do list
     *
     * @return length of to do list
     */
    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds the given task to the list
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the given task from the list
     */
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Marks the given task as done
     */
    public void markTask(Task task) {
        task.markAsDone();
    }

    /**
     * Marks the given task as undone
     */
    public void unmarkTask(Task task) {
        task.markAsUndone();
    }

    /**
     * Prints the current task list
     */
    public void printTaskList() {
        int index = 1;
        System.out.println(" Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.printf("  %d. %s\n", index, task.toString());
            index++;
        }
    }
}
