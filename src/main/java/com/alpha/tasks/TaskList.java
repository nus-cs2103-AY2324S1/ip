package com.alpha.tasks;

import java.util.ArrayList;
import java.util.List;

import com.alpha.enums.MarkEnum;
import com.alpha.exceptions.InvalidTaskException;

/**
 * The type Task list.
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
     * Instantiates a new Task list using a list of tasks.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Add task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets size of the task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Mark the given task as done.
     *
     * @param taskNumber Index of the task starting from 1.
     * @return Marked task.
     * @throws InvalidTaskException If the index is invalid.
     */
    public Task markTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > tasks.size()) {
            throw new InvalidTaskException("Task does not exist, please enter valid task number");
        }
        Task task = tasks.get(taskNumber - 1);
        task.setMark(MarkEnum.DONE);
        return task;
    }


    /**
     * Mark the given task as not done.
     *
     * @param taskNumber Index of the task starting from 1.
     * @return Unmarked task.
     * @throws InvalidTaskException If the index is invalid.
     */
    public Task unmarkTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > tasks.size()) {
            throw new InvalidTaskException("Task does not exist, please enter valid task number");
        }
        Task task = tasks.get(taskNumber - 1);
        task.setMark(MarkEnum.NOTDONE);
        return task;
    }

    /**
     * Delete the given task.
     *
     * @param taskNumber Index of the task starting from 1.
     * @return Deleted task.
     * @throws InvalidTaskException If the index is invalid.
     */
    public Task deleteTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > tasks.size()) {
            throw new InvalidTaskException("Task does not exist, please enter valid task number");
        }
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(task);
        return task;
    }
}
