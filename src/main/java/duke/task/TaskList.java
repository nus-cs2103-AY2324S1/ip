package duke.task;

import duke.userio.InvalidUserInputException;

import java.util.LinkedList;

/**
 * A list of task that handles the tasks.
 */
public class TaskList {
    protected LinkedList<Task> storage;

    /**
     * Constructor to build a TaskList.
     */
    public TaskList() {
        this.storage = new LinkedList<Task>();
    }

    /**
     * Adds a task into the TaskList.
     * @param task Task to be added into TaskList.
     */
    public void addTask(Task task) {
        this.storage.add(task);
    }

    /**
     * Retrieves task from the TaskList based on its index.
     * @param taskIndex Index of task to be retrieved.
     * @return Task of given index in TaskList.
     */
    public Task getTask(int taskIndex) {
        return this.storage.get(taskIndex);
    }

    /**
     * Deletes task with given index.
     * @param taskIndex Index of task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        this.storage.remove(taskIndex);
    }

    /**
     * Returns the number of tasks in TaskList.
     * @return Number of tasks in TaskList.
     */
    public int getSize() {
        return this.storage.size();
    }

    /**
     * Return a String that represents the numbered list of tasks in the TaskList.
     * @return String that represents the numbered list of tasks in the TaskList.
     */
    public String outputNumberedList() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.storage.size(); i++) {
            Task currTask = this.storage.get(i);
            str.append(i + 1).append(".")
                    .append(currTask.toString())
                    .append("\n");
        }
        return str.toString();
    }

    /**
     * Updates the task based on the given inputs.
     * @param taskIndex Index of task to be updated.
     * @param attributeToUpdate Attribute of task to update.
     * @param contentToUpdate Content to update the attribute of task with.
     * @throws InvalidUserInputException If the attributeToUpdate does not match any of the task's attribute.
     */
    public void updateTask(int taskIndex, String attributeToUpdate, String contentToUpdate) throws InvalidUserInputException {
        Task taskToUpdate = this.getTask(taskIndex);
        taskToUpdate.update(attributeToUpdate, contentToUpdate);
    }
}
