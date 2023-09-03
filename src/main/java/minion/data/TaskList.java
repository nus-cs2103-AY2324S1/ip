package minion.data;

import java.util.ArrayList;
import java.util.List;

import minion.common.Messages;
import minion.data.exception.IllegalValueException;
import minion.data.task.Task;

/**
 * Represents the task list of the Minion chatbot.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     * @param tasks Tasks that TaskList should contain.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList
     * @param task the given task.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if the task index is out of bounds.
     * @param taskIdx index of task.
     * @throws IllegalValueException if task index is out of bounds.
     */
    private void checkIndex(int taskIdx) throws IllegalValueException {
        if (taskIdx < 0 || taskIdx >= tasks.size()) {
            throw new IllegalValueException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }
    }

    /**
     * Marks the task with taskIdx as done.
     * @param taskIdx index of task.
     * @return the task with index taskIdx.
     * @throws IllegalValueException if taskIdx is out bounds.
     */
    public Task markTask(int taskIdx) throws IllegalValueException {
        checkIndex(taskIdx);
        Task currTask = tasks.get(taskIdx);
        currTask.setDone();
        return currTask;
    }

    /**
     * Marks the task with taskIdx as undone.
     * @param taskIdx index of task.
     * @return the task with index taskIdx.
     * @throws IllegalValueException if taskIdx is out bounds.
     */
    public Task unmarkTask(int taskIdx) throws IllegalValueException {
        checkIndex(taskIdx);
        Task currTask = tasks.get(taskIdx);
        currTask.setUndone();
        return currTask;
    }

    /**
     * Deletes the task with taskIdx.
     * @param taskIdx index of task.
     * @return the task with index taskIdx.
     * @throws IllegalValueException if taskIdx is out bounds.
     */
    public Task deleteTask(int taskIdx) throws IllegalValueException {
        checkIndex(taskIdx);
        Task currTask = tasks.get(taskIdx);
        tasks.remove(taskIdx);
        return currTask;
    }

    /**
     * Returns a TaskList containing tasks that match the query.
     * @param query the query.
     * @return a TaskList containing tasks that match the query.
     */
    public TaskList search(String query) {
        TaskList results = new TaskList();
        for (Task task: tasks) {
            if (task.contains(query)) {
                results.add(task);
            }
        }
        return results;
    }

    /**
     * Returns the size of the TaskList.
     * @return size of TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the tasks in the list.
     * @return a string representation of the tasks in the list.
     */
    public String enumerateTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n").append(i + 1).append(". ").append(tasks.get(i).toString());
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of TaskList for general display to the user.
     * @return a string representation of TaskList.
     */
    @Override
    public String toString() {
        return "Here are the tasks in your list:" + enumerateTasks();
    }

    /**
     * Returns a string representation of TaskList for displaying matched tasks.
     * @return a string representation of TaskList.
     */
    public String toStringMatching() {
        return "Here are the matching tasks in your list:" + enumerateTasks();
    }

    /**
     * Returns a string representation of TaskList for storage.
     * @return a string representation of TaskList.
     */
    public String toStringStorage() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toStringStorage()).append("\n");
        }
        return sb.toString();
    }
}
