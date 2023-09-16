package chatty.task;

import chatty.exception.InvalidTaskNumberException;

import java.util.ArrayList;

/**
 * A class that creates and handle the list of task added by the user
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList class that creaes a new ArrayList to store the tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Method that finds the current size of the task list
     * @return the size of the current task list, in int value
     */
    public int listSize() {
        int size = this.taskList.size();
        assert size >= 0 : "Task list size should not be negative.";
        return size;
    }

    /**
     * Method that helps to add in new task into the list
     * @param task
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Method that delete a task from the current list of task
     * @param i the index of the task that the user wants to delete
     * @return the task that is being removed
     * @throws InvalidTaskNumberException when the index of the task entered by the user is out of the range of the
     *                                      available task
     */
    public Task deleteTask(int i) throws InvalidTaskNumberException {
        assert i > 0 && i <= this.listSize() : "Invalid task index: " + i;
        if (i >= this.listSize() || i < 0) {
            throw new InvalidTaskNumberException();
        }
        return this.taskList.remove(i - 1);
    }

    /**
     * Method that get the task that the user want to change status
     * @param i the index of the task that the user wants to change status
     * @param status the status that user wants to change the task to, indicated by boolean value
     * @throws InvalidTaskNumberException when the index of the task entered by the user is out of the range of the
     *      *                                      available task
     */
    public void markTask(int i, boolean status) throws InvalidTaskNumberException {
        assert i > 0 && i <= this.listSize() : "Invalid task index: " + i;
        if (i >= this.listSize() || i < 0) {
            throw new InvalidTaskNumberException();
        }
        Task taskToMark = this.taskList.get(i - 1);
        taskToMark.markStatus(status);
    }

    /**
     * Method that prints out the task
     * @param i the index of the task of interest
     * @return the task indicated by the index, in String
     */
    public String showTask(int i) {
        Task taskToShow = taskList.get(i - 1);
        return taskToShow.toString();
    }

    /**
     * Method that check if the task contains the keyword. Add it into another list if it contains the keyword
     * @param keyword the keyword to search for the relevant task
     * @return the list of task matching the keyword entered
     */
    public String containsKeyword(String keyword) {
        StringBuilder listWithKeyword = new StringBuilder();
        int i = 0;
        while (i < taskList.size()) {
            if (taskList.get(i).checkKeyword(keyword)) {
                int taskNumber = i + 1;
                listWithKeyword.append(taskNumber + ". " + this.showTask(taskNumber)).append('\n');
            }
            i++;
        }
        return listWithKeyword.toString();
    }

    /**
     * Method that return the task of interest
     * @param i the index of the task to be returned
     * @return the task indicated by index i
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

}
