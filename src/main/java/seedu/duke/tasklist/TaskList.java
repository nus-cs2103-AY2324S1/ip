package seedu.duke.tasklist;

import java.util.ArrayList;

import seedu.duke.exceptions.InvalidDeadlineException;
import seedu.duke.exceptions.InvalidTaskIndexException;
import seedu.duke.exceptions.LemonException;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Task;

/**
 * The TaskList class represents a collection of tasks and provides methods
 * for editing tasks, such as adding, marking, unmarking, deleting, and retrieving tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with an existing list of tasks.
     *
     * @param tasks The ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @return String representation of the task that was marked as done.
     * @throws InvalidTaskIndexException If the provided index is invalid.
     */
    public String markTask(Integer index) throws InvalidTaskIndexException {
        try {
            Task toMark = tasks.get(index);
            toMark.markAsDone();
            return toMark.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        }
    }

    /**
     * Unmarks a task at the specified index as undone.
     * @param index The index of the task to be marked as undone.
     * @return String representation of the task that was marked as undone.
     * @throws InvalidTaskIndexException If the provided index is invalid.
     */
    public String unmarkTask(Integer index) throws InvalidTaskIndexException {
        try {
            Task toMark = tasks.get(index);
            toMark.markAsUndone();
            return toMark.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        }
    }

    /**
     * Edit the deadline date of a Deadline Task.
     * @param index the index of the task
     * @param newDeadline the new deadline to change to
     * @throws InvalidTaskIndexException thrown when provided index is invalid
     * @throws InvalidDeadlineException thrown when the provided date is invalid
     */
    public void rescheduleTask(Integer index, String newDeadline) throws LemonException {
        try {
            Task toMark = tasks.get(index);
            if (toMark instanceof Deadline) {
                Deadline toMarkDeadline = ((Deadline) toMark);
                toMarkDeadline.changeDeadline(newDeadline);
            } else {
                throw new LemonException("Only deadline tasks can be rescheduled!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        } catch (LemonException e) {
            throw new LemonException(e.getMessage());
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param taskToAdd The Task object to be added to the task list.
     * @return String representation of the task that was added.
     */
    public String addTasks(Task taskToAdd) {
        tasks.add(taskToAdd);
        return taskToAdd.toString();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskToDelete The Task object to be deleted from the task list.
     * @return String representation of the task that was deleted.
     */
    public String deleteTask(Task taskToDelete) {
        tasks.remove(taskToDelete);
        return taskToDelete.toString();
    }


    /**
     * Returns the number of tasks in the task list.
     *
     * @return Integer that represents the number of tasks in the task list.
     */
    public Integer getTasksSize() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task at the specified index from the Arraylist of tasks.
     *
     * @param i The index of the task to retrieve.
     * @return The Task object at the specified index.
     * @throws InvalidTaskIndexException If the provided index is invalid.
     */
    public Task getTask(Integer i) throws InvalidTaskIndexException {
        try {
            return tasks.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("Invalid Task Number.");
        }
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return An ArrayList containing all the tasks in the task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Returns an ArrayList of Tasks with task description that matches the keyword.
     * @param keyword the keyword to find.
     * @return An ArrayList of tasks with the matching keyword in task description.
     */
    public ArrayList<Task> findKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.getTaskList()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
