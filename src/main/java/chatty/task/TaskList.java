package chatty.task;

import java.util.ArrayList;

import chatty.exception.InvalidTaskNumberException;


/**
 * A class that creates and manages a list of tasks added by the user.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList class that creates a new ArrayList to store the tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Retrieves the current size of the task list.
     *
     * @return The size of the current task list, as an integer value.
     */
    public int listSize() {
        int size = this.taskList.size();
        assert size >= 0 : "Task list size should not be negative.";
        return size;
    }


    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the current list of tasks.
     *
     * @param i The index of the task that the user wants to delete.
     * @return The task that is being removed.
     * @throws InvalidTaskNumberException When the index of the task entered by the user is out of the range of the
     *                                   available tasks.
     */
    public Task deleteTask(int i) throws InvalidTaskNumberException {
        assert i > 0 && i <= this.listSize() : "Invalid task index: " + i;
        if (i > this.listSize() || i < 0) {
            throw new InvalidTaskNumberException();
        }
        return this.taskList.remove(i - 1);
    }

    /**
     * Retrieves the task that the user wants to change status.
     *
     * @param i      The index of the task that the user wants to change status.
     * @param status The status that the user wants to change the task to, indicated by a boolean value.
     * @throws InvalidTaskNumberException When the index of the task entered by the user is out of the range of the
     *                                   available tasks.
     */
    public void markTask(int i, boolean status) throws InvalidTaskNumberException {
        assert i > 0 && i <= this.listSize() : "Invalid task index: " + i;
        if (i > this.listSize() || i < 0) {
            throw new InvalidTaskNumberException();
        }
        Task taskToMark = this.taskList.get(i - 1);
        taskToMark.markStatus(status);
    }

    /**
     * Prints out the task at the specified index.
     *
     * @param i The index of the task of interest.
     * @return The task indicated by the index, as a String.
     */
    public String showTask(int i) {
        Task taskToShow = taskList.get(i - 1);
        return taskToShow.toString();
    }

    /**
     * Checks if the tasks contain a specific keyword and returns a list of tasks matching the keyword.
     *
     * @param keyword The keyword to search for relevant tasks.
     * @return A list of tasks matching the entered keyword.
     */
    public String containsKeyword(String keyword) {
        StringBuilder listWithKeyword = new StringBuilder();
        int i = 0;
        while (i < taskList.size()) {
            if (taskList.get(i).checkKeyword(keyword)) {
                int taskNumber = i + 1;
                listWithKeyword.append(taskNumber).append(". ").append(this.showTask(taskNumber)).append('\n');
            }
            i++;
        }
        return listWithKeyword.toString();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param i The index of the task to be returned.
     * @return The task indicated by index i.
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

}
