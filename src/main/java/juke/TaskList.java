package juke;

import java.util.ArrayList;
/**
 * Handles task storage in the current session.
 *
 * @author lshaoqin
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets the total number of tasks.
     * @return the number of tasks in this TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task as undone.
     * @param index the index of the task
     * @return The task after it has been modified
     * @throws JukeError if the task does not exist
     */
    public Task markAsUndone(int index) throws JukeError {
        if (index > tasks.size()) {
            throw new JukeError("That task does not exist!");
        }
        Task currTask = tasks.get(index - 1);
        currTask.markAsUndone();
        return currTask;
    }

    /**
     * Marks a task as done.
     * @param index the index of the task
     * @return The task after it has been modified
     * @throws JukeError if the task does not exist
     */
    public Task markAsDone(int index) throws JukeError {
        if (index > tasks.size()) {
            throw new JukeError("That task does not exist!");
        }
        Task currTask = tasks.get(index - 1);
        currTask.markAsDone();
        return currTask;
    }

    /**
     * Deletes a task.
     * @param index the index of the task
     * @return The task after it has been deleted
     * @throws JukeError if the task does not exist
     */
    public Task delete(int index) throws JukeError {
        if (index > tasks.size()) {
            throw new JukeError("That task does not exist!");
        }
        Task currTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        return currTask;
    }
    public Task addTag(int index, String tagName) throws JukeError {
        if (index > tasks.size()) {
            throw new JukeError("That task does not exist!");
        }
        Task currTask = tasks.get(index - 1);
        currTask.addTag(tagName);
        return currTask;
    }

    /**
     * Finds all tasks which corresponds to a search term.
     * @param searchTerm The term to search for
     * @return An ArrayList of all corresponding tasks
     */
    public ArrayList<Task> find(String searchTerm) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.desc.contains(searchTerm)) {
                results.add(task);
            }
        }
        return results;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
