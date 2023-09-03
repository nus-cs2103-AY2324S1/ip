package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskList;

    public TaskList(DataStorage store) {
        taskList = store.tasks;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.remove(taskIndex);
        }
    }

    public void mark(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.get(taskIndex).markAsDone();
        }
    }

    public void unmark(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.get(taskIndex).markAsUndone();
        }
    }

    /**
     * Returns the task corresponding to the given task index.
     *
     * @param taskIndex the index of the task that is to be retrieved.
     * @return The task corresponding to the given task index.
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    /**
     * Returns the number of tasks in the taskList..
     *
     * @return The length of the taskList.
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * Returns the ArrayList of tasks.
     *
     * @return The ArrayList of tasks..
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}
