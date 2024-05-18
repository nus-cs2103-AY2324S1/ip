package duke;

import java.util.ArrayList;

import duke.task.Task;


/**
 * Data Structure that stores the chatbot's tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */


    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the size of the tasklist.
     * @return the integer size of the tasklist.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the task.
     * @param index the task's index in the TaskList.
     * @return the Task.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds the task to the TaskList.
     * @param task the Task that will be added.
     */

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the task from the TaskList.
     * @param index the task's index in the TaskList.
     */
    public void deleteTask(int index) {
        assert index > 0 && index < tasks.size();
        this.tasks.remove(index);
    }

    /**
     * Deletes many tasks from the taskList at once
     * @param indices
     */
    public void deleteManyTasks(int[] indices) {
        boolean[] toDelete = new boolean[tasks.size()];

        for (int index : indices) {
            if (index > 0 && index <= tasks.size()) {
                toDelete[index - 1] = true;
            }
        }

        ArrayList<Task> newTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (!toDelete[i]) {
                newTasks.add(tasks.get(i));
            }
        }

        tasks = newTasks;
    }

    /**
     * Marks a particular task as done.
     * @param index the index of the task that is to be marked.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a particular task as not done.
     * @param index the index of the task that is to be unmarked.
     */

    public void markTaskAsNotDone(int index) {
        assert index > 0 && index < tasks.size();
        this.tasks.get(index).markAsNotDone();
    }

}
