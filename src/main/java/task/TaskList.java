package task;

import java.util.ArrayList;

/**
 * Class that handles task management.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the specified tasks.
     *
     * @param tasks The list of tasks to initialize the task list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a new task list after adding the specified task.
     *
     * @param task Task to be added.
     * @return Task list with added task.
     */
    public TaskList addTask(Task task) {
        ArrayList<Task> updatedTasks = this.tasks;
        updatedTasks.add(task);
        return new TaskList(updatedTasks);
    }

    /**
     * Returns a new task list after deleting the specified task.
     *
     * @param task Task to be removed.
     * @return Task list after removing task.
     */
    public TaskList deleteTask(Task task) {
        ArrayList<Task> updatedTasks = this.tasks;
        updatedTasks.remove(task);
        return new TaskList(updatedTasks);
    }

    /**
     * Returns a task based on the specified task number.
     *
     * @param taskNumber The task number.
     * @return The task corresponding to the task number.
     */
    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Returns a new task list with the specified task marked.
     *
     * @param numberToMark The task number to be marked.
     * @return Task list updated with the marked task.
     */
    public TaskList markTask(int numberToMark) {
        int index = numberToMark - 1;
        ArrayList<Task> updatedTasks = this.tasks;
        Task taskToMark = updatedTasks.get(index);
        taskToMark = taskToMark.mark();
        updatedTasks.set(index, taskToMark);
        return new TaskList(updatedTasks);
    }

    /**
     * Checks if a task is present in the task list.
     *
     * @param t The task to check.
     * @return True if the task is present, false otherwise.
     */
    public boolean isTaskPresent(Task t) {
        return this.tasks.contains(t);
    }

    /**
     * Checks if there is a conflict with a given task.
     *
     * @param task The task to check for conflicts with.
     * @return True if there is a conflict, false otherwise.
     */
    public boolean hasConflict(Task task) {
        return this.tasks.stream().anyMatch(t -> task.hasConflictWith(t));
    }

    /**
     * Returns a new task list with the specified task unmarked.
     *
     * @param numberToUnmark The task number to be unmarked.
     * @return Task list updated with the unmarked task.
     */
    public TaskList unmarkTask(int numberToUnmark) {
        int index = numberToUnmark - 1;
        ArrayList<Task> updatedTasks = this.tasks;
        Task taskToUnmark = updatedTasks.get(index);
        taskToUnmark = taskToUnmark.unmark();
        updatedTasks.set(index, taskToUnmark);
        return new TaskList(updatedTasks);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns a task list of tasks containing a similar query term.
     *
     * @param query The query term to search for in task names.
     * @return Task list containing similar tasks.
     */
    public TaskList getSimilarTasks(String query) {
        TaskList similarTasks = new TaskList();
        for (Task t : this.tasks) {
            if (t.getName().contains(query)) {
                similarTasks = similarTasks.addTask((t));
            }
        }
        return similarTasks;
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            outputString.append(String.format("%d. %s", i + 1, task.toString()));
            if (i < this.tasks.size() - 1) {
                outputString.append("\n");
            }
        }
        if (this.tasks.isEmpty()) {
            outputString.append("You have no pending tasks.");
        }
        return outputString.toString();
    }
}
