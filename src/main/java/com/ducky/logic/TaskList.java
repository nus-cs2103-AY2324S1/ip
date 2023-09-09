package com.ducky.logic;

import java.util.ArrayList;

import com.ducky.command.DuckyInvalidTaskIndexException;
import com.ducky.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Adds the specified task into the list.
     * @param t Task to be added into the list.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Marks the task in the list with the specified index as complete.
     * @param index Index of completed task in the list.
     * @return Task to be marked as complete.
     * @throws DuckyInvalidTaskIndexException If specified index does not exist.
     */
    public Task markTaskAsComplete(int index) throws DuckyInvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DuckyInvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toMark = this.tasks.get(index);
        toMark.setComplete();
        return toMark;
    }

    /**
     * Marks the task in the list with the specified index as incomplete.
     * @param index Index of incomplete task in the list.
     * @return Task to be marked as incomplete.
     * @throws DuckyInvalidTaskIndexException If specified index does not exist.
     */
    public Task markTaskAsIncomplete(int index) throws DuckyInvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DuckyInvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toUnmark = this.tasks.get(index);
        toUnmark.setIncomplete();
        return toUnmark;
    }

    /**
     * Deletes the task in the list with the specified index.
     * @param index Index of task to be deleted.
     * @return Task to be deleted.
     * @throws DuckyInvalidTaskIndexException If specified index does not exist.
     */
    public Task deleteTask(int index) throws DuckyInvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DuckyInvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toDelete = this.tasks.get(index);
        this.tasks.remove(index);
        return toDelete;
    }

    /**
     * Returns a string representation of tasks in the task list
     * that contain the specified query string.
     * @param query Substring to find in the task list.
     * @return String representation of tasks that match the query.
     */
    public String getTaskQueryResult(String query) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.containsString(query)) {
                result.append(String.format("%d.%s\n", i + 1, t));
            }
        }

        return result.toString().isEmpty()
                ? String.format("Sorry, I couldn't find any tasks that contain \"%s\".", query)
                : String.format("Here are the task(s) that contain \"%s\":\n%s", query, result);
    }

    /**
     * Returns a string representation of the list for printing to user interface.
     * @return Representation of the list meant for printing.
     */
    public String getPrintableList() {
        if (this.tasks.isEmpty()) {
            return "There are no tasks in your list.";
        }
        StringBuilder builder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                builder.append(String.format("%d.%s", i + 1, this.tasks.get(i)));
            } else {
                builder.append(String.format("%d.%s\n", i + 1, this.tasks.get(i)));
            }
        }
        return builder.toString();
    }

    /**
     * Returns a string representation of the list for saving to file system.
     * @return Representation of the file meant for saving.
     */
    public String getSaveableList() {
        StringBuilder text = new StringBuilder();
        for (Task t : this.tasks) {
            text.append(String.format("%s\n", t.getSaveFormat()));
        }
        return text.toString();
    }

    /**
     * Returns a string representation of the number of tasks in the list.
     * @return Representation of task count.
     */
    public String getListLengthStatus() {
        if (this.tasks.isEmpty()) {
            return "There are no tasks in your list.";
        } else if (this.tasks.size() == 1) {
            return "There is now 1 task in your list.";
        } else {
            return String.format("There are now %d tasks in your list.", this.tasks.size());
        }
    }
}
