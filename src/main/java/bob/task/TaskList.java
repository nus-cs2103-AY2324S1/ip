package bob.task;

import bob.exception.BobInvalidTaskNumberException;

import java.util.ArrayList;

/**
 * Represents a list of Tasks that can contain date and be mark/unmark as done.
 * Acts as a dynamic data structure to be manipulated by user.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor of a TaskList.
     * Instantiates an empty array list to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of a TaskList.
     * Instantiates an array list of tasks based on given argument.
     *
     * @param tasks ArrayList of tasks to instantiate TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task into current task list.
     *
     * @param task Task to be added into list
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes task in current task list based on index given.
     * Input index is expected to start from 1 instead of 0.
     *
     * @param num Index of task to be deleted
     * @return Instance of deleted task
     * @throws BobInvalidTaskNumberException If index provided is out of bounds.
     */
    public Task deleteTask(int num) throws BobInvalidTaskNumberException {
        try {
            return tasks.remove(num - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobInvalidTaskNumberException();
        }
    }

    /**
     * Returns number of current tasks.
     *
     * @return number of current tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Fetches task from current task list based on provided index.
     * Index is expected to start from 0.
     *
     * @param num Index of task to be fetched
     * @return The task at the given index
     * @throws BobInvalidTaskNumberException if index provided is out of bounds
     */
    public Task getTask(int num) throws BobInvalidTaskNumberException {
        try {
            return this.tasks.get(num);
        } catch (IndexOutOfBoundsException e) {
            throw new BobInvalidTaskNumberException("You are trying to access a non-existent task :O\n"
                    + "Use the command: \"list\" to find out what tasks you have.");
        }
    }

    /**
     * Creates a new TaskList by filtering existing TaskList
     * with provided keyword.
     *
     * @param keyword Keyword used to filter task descriptions
     * @return A filtered TaskList
     */
    public TaskList keywordFilter(String keyword) {
        TaskList filteredTasklist = new TaskList();
        if (keyword.isBlank()) {
            return this;
        }
        for (Task currentTask : this.tasks) {
            if (currentTask.containsKeyword(keyword)) {
                filteredTasklist.addTask(currentTask);
            }
        }
        return filteredTasklist;
    }
}
