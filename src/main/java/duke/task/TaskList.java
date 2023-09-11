package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Duke application.
 * This class provides methods like adding, deleting, marking, and retrieving tasks.
 */
public class TaskList {
    protected ArrayList<Task> arrTask;

    /**
     * Constructs a TaskList object with an existing list of tasks.
     *
     * @param arrTask An ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> arrTask) {
        this.arrTask = arrTask;
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.arrTask = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return arrTask.size();
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void mark(int index) {
        arrTask.get(index).markAsDone();
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param index The index of the task to mark as undone.
     */
    public void unmark(int index) {
        arrTask.get(index).markAsUndone();
    }

    /**
     * Returns a formatted string representation of the task at the specified index for display.
     *
     * @param index The index of the task to retrieve the display string for.
     * @return A formatted string representation of the task for display purposes.
     */
    public String getPrint(int index) {
        return arrTask.get(index).toString();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.arrTask.add(task);
    }

    /**
     * Deletes a task at the specified index from the TaskList.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.arrTask.remove(index);
    }

    /**
     * Retrieves a task at the specified index from the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.arrTask.get(index);
    }

    public boolean contains(String taskString) {
        boolean isContain = false;
        for (int i = 0; i < arrTask.size(); i++) {
            isContain = isContain || arrTask.get(i).toString().equals(taskString);
            arrTask.get(i).isDone = !arrTask.get(i).isDone;
            isContain = isContain || arrTask.get(i).toString().equals(taskString);
            arrTask.get(i).isDone = !arrTask.get(i).isDone;
        }
        return isContain;
    }
}
