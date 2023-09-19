package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.IntStream;

import duke.task.Task;

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
        assert this.tasks != null : "tasks should have been initialised and should not be null";
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
        assert this.tasks != null : "tasks should have been initialised and should not be null";
        return this.tasks.remove(taskNum);
    }

    /**
     * Marks the task at a given index as done.
     *
     * @param taskNum The index to mark the task.
     * @return The marked task.
     */
    public Task doTask(int taskNum) {
        assert this.tasks != null : "tasks should have been initialised and should not be null";
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
        assert this.tasks != null : "tasks should have been initialised and should not be null";
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
        assert this.tasks != null : "tasks should have been initialised and should not be null";
        return this.tasks.size();
    }

    /**
     * Converts the tasks in the list into the correct format of String.
     *
     * @return Formatted tasks Strings.
     */
    public String saveToStorage() {
        assert this.tasks != null : "tasks should have been initialised and should not be null";
        StringBuilder content = new StringBuilder();
        for (Task task : this.tasks) {
            content.append(task.toSaveFormat());
            content.append(System.lineSeparator());
        }
        return content.toString();
    }

    @Override
    public String toString() {
        assert this.tasks != null : "tasks should have been initialised and should not be null";
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, this.tasks.size())
                .mapToObj(i -> "    " + (i + 1) + ". " + this.tasks.get(i) + System.lineSeparator())
                .forEach(sb::append);
        return sb.toString();
    }

    /**
     * Finds all the tasks in the list that falls on the given date.
     * No todos will be returned, since there is no dates
     * Events will be returned only if the input date is within the date range of Event
     * Deadline will be returned if the deadline is the same as date input
     *
     * @param date The date to check.
     * @return String of all tasks on the date.
     */
    public String findTasksOnDate(LocalDate date) {
        assert this.tasks != null : "tasks should have been initialised and should not be null";
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, this.tasks.size())
                .filter(i -> this.tasks.get(i).isOnDate(date))
                .mapToObj(i -> "    " + (i + 1) + ". " + this.tasks.get(i) + System.lineSeparator())
                .forEach(sb::append);
        return sb.toString();
    }

    /**
     * Finds the list of task according to description.
     *
     * @param description The input used to search for tasks.
     * @return A string containing lines of tasks.
     */
    public String find(String description) {
        assert this.tasks != null : "tasks should have been initialised and should not be null";
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, this.tasks.size())
                .filter(i -> this.tasks.get(i).containsDescription(description))
                .mapToObj(i -> "    " + (i + 1) + ". " + this.tasks.get(i) + System.lineSeparator())
                .forEach(sb::append);
        return sb.toString();
    }

    /**
     * Finds the list of task according to location given.
     *
     * @param location The input used to search for tasks.
     * @return A string containing lines of tasks.
     */
    public String findByLocation(String location) {
        assert this.tasks != null : "tasks should have been initialised and should not be null";
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, this.tasks.size())
                .filter(i -> this.tasks.get(i).containsLocation(location))
                .mapToObj(i -> "    " + (i + 1) + ". " + this.tasks.get(i) + System.lineSeparator())
                .forEach(sb::append);
        return sb.toString();
    }

}
