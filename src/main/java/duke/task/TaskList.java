package duke.task;


import java.util.ArrayList;

/**
 * Represents the list of a task.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList.
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Marks the specific task.
     *
     * @param index an integer representing the index of a task that will be marked.
     */
    public void mark(Integer index) {
        tasks.get(index).mark();
    }

    /**
     * Unmarks the specific task.
     *
     * @param index an integer representing the index of a task that will be unmarked.
     */
    public void unmark(Integer index) {
        tasks.get(index).unmark();
    }

    /**
     * Return the specific task
     *
     * @param index an integer representing the index of a task.
     */
    public Task getTask(Integer index) {
        return tasks.get(index);
    }

    /**
     * Returns a String representing the specific task that will be stored.
     *
     * @param index an integer representing the index of a task.
     * @return the String representing the specific task that will be stored.
     */
    public String getTaskInput(Integer index) {
        return tasks.get(index).getInput();
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list.
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Add a task to the list
     *
     * @param task the task that will be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Delete the specified task.
     *
     * @param index an integer representing the index of a task.
     */
    public void delete(Integer index) {
        tasks.remove(index);
    }

    /**
     * Returns the String representation of a TaskList.
     *
     * @return the String representation of a TaskList.
     */
    @Override
    public String toString() {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            taskList += (i + 1) + "." + tasks.get(i) + "\n";
        }
        return taskList;
    }
}
