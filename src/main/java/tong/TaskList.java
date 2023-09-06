package tong;

import tong.exception.TaskNotFoundException;
import tong.task.Task;
import tong.exception.DuplicatedMarkException;

import java.util.ArrayList;

/**
 * Represents a TaskList containing a list of tong.task.Task to be done.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    @Override
    public String toString() {
        if (this.taskList.isEmpty()) {
            return "Oops! Your to-do list is empty. Add some tasks now:)";
        }

        String result = "YOUR TO-DO LIST:\n";
        for (int i = 0; i < this.taskList.size(); i++) {
            int order = i + 1;
            result+= order + ". " + taskList.get(i) +"\n";
        }
        result+= "----------END OF YOUR TO-DO LIST----------\n";
        result+= taskList.size() + " tasks in total";
        return result;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the Task indicating by the input index.
     *
     * @param targetVisibleIndex Index of the target Task that is visible to the users.
     * @return The target Task.
     * @throws TaskNotFoundException If input index is out of bound.
     */
    public Task getTask(int targetVisibleIndex) throws TaskNotFoundException {
        int targetInvisibleIndex = targetVisibleIndex - 1;
        if (targetInvisibleIndex >= taskList.size() || targetVisibleIndex < 1) {
            throw new TaskNotFoundException("Input task index out of bound.");
        }
        return taskList.get(targetInvisibleIndex);
    }

    /**
     * Adds a new Task into the TaskList.
     *
     * @param task Task to be added into the TaskList.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the Task indicating by the input index as done.
     *
     * @param task Task to be marked as done.
     * @throws DuplicatedMarkException If the target Task has already been marked as done.
     */
    public void markTask(Task task) throws DuplicatedMarkException {
        task.markAsDone();
    }

    /**
     * Marks the Task indicating by the input index as not done.
     *
     * @param task Task to be marked as not done.
     * @throws DuplicatedMarkException If the target Task is currently marked as not done.
     */
    public void unmarkTask(Task task) throws DuplicatedMarkException {
        task.markAsNotDone();
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param task Task to be deleted from the TaskList.
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }
}
