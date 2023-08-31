package alpha;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Class that stores the current list of tasks.
 *
 * @author Wong Joon Hung
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList class. Creates a new ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the current size of the task list.
     *
     * @return the current size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a task to the current task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the task at the given index.
     *
     * @param i The index of the returned task.
     * @return The task at the given index.
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Marks a task at the given index and returns it.
     *
     * @param index The index of the marked and returned task.
     * @return The marked task at the given index.
     */
    public Task mark(int index) {
        Task curr = getTask(index - 1); // Decrement by 1 to match display index
        curr.mark();
        return curr;
    }

    /**
     * Unmarks a task at the given index and returns it.
     *
     * @param index The index of the unmarked and returned task.
     * @return The unmarked task at the given index.
     */
    public Task unmark(int index) {
        Task curr = getTask(index - 1); // Decrement by 1 to match display index
        curr.unmark();
        return curr;
    }

    /**
     * Deletes a task at the given index and returns it.
     *
     * @param index The index of the deleted and returned task.
     * @return The deleted task at the given index.
     */
    public Task delete(int index) {
        Task curr = taskList.get(index - 1);
        taskList.remove(index - 1);
        return curr;
    }


    public TaskList search(String input) {
        TaskList newList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            if (curr.getDescription().toUpperCase().contains(input.toUpperCase())) {
                newList.add(curr);
            }
        }
        return newList;
    }
}
