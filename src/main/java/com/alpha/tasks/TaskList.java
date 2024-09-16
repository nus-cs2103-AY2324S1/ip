package com.alpha.tasks;

import java.util.ArrayList;
import java.util.List;

import com.alpha.enums.MarkEnum;

/**
 * The Task list class.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Instantiates a new Task list with a given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Add task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Mark task as done.
     *
     * @param taskNumber The index of the task.
     * @return The marked task.
     */
    public Task markTask(int taskNumber) {
        assert taskNumber > 0 && taskNumber <= getSize();
        Task task = tasks.get(taskNumber - 1);
        task.setMark(MarkEnum.DONE);
        return task;
    }

    /**
     * Mark task as undone.
     *
     * @param taskNumber The index of the task.
     * @return The unmarked task.
     */
    public Task unmarkTask(int taskNumber) {
        assert taskNumber > 0 && taskNumber <= getSize();
        Task task = tasks.get(taskNumber - 1);
        task.setMark(MarkEnum.NOTDONE);
        return task;
    }

    /**
     * Delete task from task list..
     *
     * @param taskNumber The index of the task.
     * @return The deleted task.
     */
    public Task deleteTask(int taskNumber) {
        assert taskNumber > 0 && taskNumber <= getSize();
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(task);
        return task;
    }
}
