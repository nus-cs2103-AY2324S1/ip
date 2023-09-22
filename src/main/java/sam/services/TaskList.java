package sam.services;

import sam.constants.Message;
import sam.exceptions.DukeException;
import sam.tasks.Task;

import java.util.ArrayList;

/**
 * Represents the entire task list. Contains the data of the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Provide a copy of the entire taskList.
     *
     * @return the copy of the taskList
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Provide size of task list.
     *
     * @return the size of the taskList
     */
    public Integer getSize() {
        return tasks.size();
    }

    /**
     * Add task to task list.
     *
     * @param task the new task created
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets specific tasks according to their index number in the task list.
     *
     * @param index the index number of the task in the task list
     * @return the task requested
     */
    public Task getTask(Integer index) {
        return tasks.get(index);
    }

    /**
     * Provides the task list in a list format.
     *
     * @return the taskList string
     */
    public String listTasks() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException(Message.TASK_LIST_EMPTY);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\t");
            sb.append(i + 1);
            sb.append(".");
            sb.append(tasks.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Mark a specific task as done according to their index number in the task list.
     *
     * @param index the index number of the task in the task list
     */
    public void markTaskAsDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(Message.TASK_LIST_INVALID_INDEX);
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Mark a specific task as not done according to their index number in the task list.
     *
     * @param index the index number of the task in the task list
     */
    public void markTaskAsNotDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(Message.TASK_LIST_INVALID_INDEX);
        }
        tasks.get(index).markAsNotDone();
    }

    /**
     * Mark a specific task as done according to their index number in the task list.
     *
     * @param index the index number of the task in the task list
     */
    public void addTagToTask(int index, String description) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(Message.TASK_LIST_INVALID_INDEX);
        }
        tasks.get(index).addTag(description);
    }

    /**
     * Delete a specific task according to their index number in the task list.
     *
     * @param index the index number of the task in the task list
     */
    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(Message.TASK_LIST_INVALID_INDEX);
        }
        return tasks.remove(index);
    }

    /**
     * Prints the total number of tasks left in the list.
     *
     * @return the total number of tasks left in the list
     */
    public String getTaskCountSummary() {
        int size = tasks.size();
        if (size == 1) {
            return ("Now you have " + size + " task in the list.");
        } else {
            return ("Now you have " + size + " tasks in the list.");
        }
    }

    /**
     * Search for similar tasks in task list.
     *
     * @param word user input for search
     * @return Task list of similar tasks
     */
    public TaskList findTasks(String word) throws DukeException {
        TaskList tasksFound = new TaskList();
        for (Task t : tasks) {
            if (t.getDescription().contains(word)) {
                tasksFound.addTask(t);
            }
        }
        if (tasksFound.getSize() == 0) {
            throw new DukeException(Message.TASK_NOT_FOUND);
        }
        return tasksFound;
    }
}
