package duke.management;

import java.util.ArrayList;

import duke.DukeException;
import duke.task.Task;

/**
 * Contains an Arraylist of all tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedFile) {
        this.tasklist = loadedFile;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasklist.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted Task.
     */
    public Task deleteTask(int index) {
        try {
            Task deletedTask = tasklist.get(index);
            tasklist.remove(index);
            return deletedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format(DukeException.NO_SUCH_TASK));
        }
    }

    /**
     * Gets the Task at a particular index.
     *
     * @param index The index of the wanted Task.
     * @return The Task at that index.
     */
    public Task getTask(int index) {
        try {
            return tasklist.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format(DukeException.NO_SUCH_TASK));
        }
    }

    /**
     * Returns the list of the tasks.
     *
     * @return The list.
     */
    public ArrayList<Task> getTasks() {
        return tasklist;
    }

    /**
     * Returns the size of the tasklist.
     *
     * @return Size of the list.
     */
    public int getSize() {
        return tasklist.size();
    }

    /**
     * Prints all the tasks in the tasklist.
     */
    public String printTasks() {
        String response = "Here are the tasks in your list: \n";
        for (int i = 0; i < tasklist.size(); i++) {
            int index = i + 1;
            Task t = tasklist.get(i);
            response += index + ". " + t.toString() + "\n";
        }
        return response;
    }
}
