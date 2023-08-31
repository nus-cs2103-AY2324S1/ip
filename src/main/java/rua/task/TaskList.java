package rua.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object (with no tasks inside by default).
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a Task object.
     *
     * @param tasks An ArrayList of Task to represent the existing tasks in the TaskList or
     *              tasks that will be added into the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the ArrayList of Task objects in the TaskList.
     *
     * @return The ArrayList of Task objects in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a new TaskList object with updated list of tasks.
     *
     * @param tasks An ArrayList of Task objects in the new TaskList.
     * @return The new TaskList object with updated list of tasks.
     */
    private TaskList update(ArrayList<Task> tasks) {
        return new TaskList(tasks);
    }

    /**
     * Adds a Task object to the TaskList.
     *
     * @param task A Task object that will be added into the TaskList.
     * @return The new TaskList object with the new Task.
     */
    public TaskList add(Task task) {
        ArrayList<Task> currentTasks = this.tasks;
        currentTasks.add(task);
        return update(currentTasks);
    }

    /**
     * Deletes a Task object with a given index from the TaskList.
     *
     * @param index An int index of the Task that will be removed.
     * @return The new TaskList object after removing the task.
     */
    public TaskList delete(int index) {
        ArrayList<Task> currentTasks = this.tasks;
        currentTasks.remove(index - 1);
        return update(currentTasks);
    }

    /**
     * Sets a Task object with a given index in the TaskList to be marked.
     *
     * @param index An int index of the Task that will be marked.
     * @return The new TaskList object after marking the task.
     */
    public TaskList mark(int index) {
        ArrayList<Task> currentTasks = this.tasks;
        Task target = currentTasks.get(index - 1);
        currentTasks.set(index - 1, target.setMarked());
        return update(currentTasks);
    }

    /**
     * Sets a Task object with a given index in the TaskList to be unmarked.
     *
     * @param index An int index of the Task that will be unmarked.
     * @return The new TaskList object after unmarking the task.
     */
    public TaskList unmark(int index) {
        ArrayList<Task> currentTasks = this.tasks;
        Task target = currentTasks.get(index - 1);
        currentTasks.set(index - 1, target.setUnmarked());
        return update(currentTasks);
    }

    /**
     * Returns a String with information of all tasks happening on a given date.
     *
     * @param date A date that will be searched.
     * @return A String with information of all tasks happening on a given date.
     */
    public String searchByDate(LocalDate date) {
        String res= "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isHappeningOnThatDate(date)) {
                res = res + Integer.toString(i + 1) +
                        ": " + tasks.get(i).toString() + "\n";
            }
        }
        return res;
    }

    /**
     * Returns a string to represent this TaskList.
     *
     * @return A string to represent this TaskList.
     */
    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result = result + (i + 1) + ": " + tasks.get(i).toString() + "\n";
        }
        return result;
    }
}
