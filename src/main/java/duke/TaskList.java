package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represents and handles a list of tasks in application.
 */
public class TaskList {

    protected ArrayList<Task> taskList;

    /**
     * Initialises a new TaskList object.
     * @param store DataStorage to access saved tasks.
     */
    public TaskList(DataStorage store) {
        taskList = store.taskDataList;
    }

    /**
     * Adds a specified task from taskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a specified task from taskList.
     *
     * @param taskIndex Index of task to be deleted.
     * @throws DukeException If error produced during execution.
     */
    public void delete(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.remove(taskIndex);
        }
    }

    /**
     * Marks a specified task from taskList.
     *
     * @param taskIndex Index of task to be marked.
     * @throws DukeException If error produced during execution.
     */
    public void mark(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.get(taskIndex).markAsDone();
        }
    }

    /**
     * Unmarks a specified task from taskList.
     *
     * @param taskIndex Index of task to be unmarked.
     * @throws DukeException If error produced during execution.
     */
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
     * Returns the number of tasks in the taskList.
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

    /**
     * Filters taskList to find tasks containing given keyword.
     *
     * @param keyword String keyword to find tasks containing it.
     * @return ArrayList containing tasks with given keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                filteredList.add(taskList.get(i));
            }
        }
        return filteredList;
    }

    /**
     * Updates description of a specified task from taskList.
     *
     * @param taskIndex Index of task to be updated.
     * @param taskInformation New description.
     */
    public void updateDescription(int taskIndex, String taskInformation) {
        taskList.get(taskIndex).updateTaskDescription(taskInformation);
    }

    /**
     * Updates date of a specified task from taskList.
     *
     * @param taskIndex Index of task to be updated.
     * @param fieldToUpdate Date field to be updated.
     * @param newDate New date.
     */
    public void updateDate(int taskIndex, String fieldToUpdate, LocalDateTime newDate) {
        taskList.get(taskIndex).updateDate(fieldToUpdate, newDate);
    }

    /**
     * Clears all data in taskList.
     */
    public void clear() {
        this.taskList.clear();
    }

}
