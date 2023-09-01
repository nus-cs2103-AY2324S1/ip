package duke.task;

import duke.DukeException;

import java.util.ArrayList;

/**
 * Represents a list of tasks in Duke.
 */
public class TaskList extends ArrayList<Task>{
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Lists all the tasks in the TaskList.
     */
    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("\t No tasks added yet.");
        } else {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int listSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        // Add task to the list
        tasks.add(task);
    }

    /**
     * Gets a task at the specified index in the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws DukeException If the index is out of bounds.
     */
    public Task getTask(int index) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new DukeException("\t ☹ OOPS!!! Task not found.");
        }
    }

    /**
     * Deletes a task at the specified index from the TaskList.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException If the index is out of bounds.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("\t ☹ OOPS!!! Please enter a" +
             " valid task index to delete.");
        }
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws DukeException If the index is out of bounds.
     */
    public void markTaskAsDone(int index) throws DukeException {
        // Mark task as done
        try {
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markDone();
            } else {
                throw new DukeException("☹ OOPS!!! Task not found.");
            }
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @throws DukeException If the index is out of bounds.
     */
    public void markTaskAsUndone(int index) throws DukeException {
        // Mark task as undone
        try {
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markUndone();
            } else {
                throw new DukeException("☹ OOPS!!! Task not found.");
            }
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    /**
     * Finds tasks containing a specific keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A TaskList containing tasks with descriptions that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
