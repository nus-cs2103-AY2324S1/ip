package duke;

import java.util.ArrayList;

/**
 * TaskList class.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     * @param tasklist an array list of tasks
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.tasks = tasklist;
    }

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    /**
     * Get the task according to index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Add new task.
     * @param newtask a new Task
     */
    public void addTask(Task newtask) {
        this.tasks.add(newtask);
    }

    /**
     * Delete a task according to index.
     * @param index index of the task, 0-indexed
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Mark the task as done.
     * @param index index of the task
     */
    public void markTask(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Mark the task as not done.
     * @param index the index of the task
     */
    public void unmarkTask(int index) {
        this.tasks.get(index).markAsNotDone();
    }

    /**
     * GetSize of the task list.
     * @return the getSize
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Search task according to the query given.
     * @param query search query string
     * @return a TaskList with all the search result inside
     */
    public TaskList searchTask(String query) {
        TaskList results = new TaskList();
        for (Task task : tasks) {
            if (task.description.toUpperCase().contains(query.toUpperCase())) {
                results.addTask(task);
            }
        }
        return results;
    }
}
