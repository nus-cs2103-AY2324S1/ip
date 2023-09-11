package duke.task;

import java.util.ArrayList;

/**
 * Represents a TaskList class that deals with the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param list List of tasks to initialise the list with.
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Gets a task from the list.
     *
     * @param index Index of the task to be retrieved.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked.
     */
    public void markTask(int index) {
        this.tasks.get(index).mark();
    }

    /**
     * Unmarks a task as done.
     *
     * @param index Index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        this.tasks.get(index).unmark();
    }

    /**
     * Returns an ArrayList of tasks that contains the keyword
     * being searched.
     *
     * @param keyword Keyword to be searched for in the description.
     * @return ArrayList of tasks that contains the keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Returns the size of the list.
     *
     * @return Int of the size of the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the tasks as an ArrayList.
     *
     * @return ArrayList of task list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;

    }

    /**
     * Returns the list as a string.
     *
     * @return String of the list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String format = "%d. %s";
            if (i < tasks.size() - 1) {
                sb.append(String.format(format + " \n", i + 1, tasks.get(i).toString()));
            } else {
                sb.append(String.format(format, i + 1, tasks.get(i).toString()));
            }
        }
        return sb.toString();
    }

}
