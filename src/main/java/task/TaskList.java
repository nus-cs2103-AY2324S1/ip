package task;

import java.util.ArrayList;

/**
 * Class that handles task management
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a new task list with added task.
     *
     * @param task Task to be added
     * @return Task list with added task
     */
    public TaskList addTask(Task task) {
        ArrayList<Task> updatedTasks = this.tasks;
        updatedTasks.add(task);
        return new TaskList(updatedTasks);
    }

    /**
     * Returns a new task list after deleting task.
     *
     * @param task Task to be removed
     * @return Task list after removing task
     */
    public TaskList deleteTask(Task task) {
        ArrayList<Task> updatedTasks = this.tasks;
        updatedTasks.remove(task);
        return new TaskList(updatedTasks);
    }

    /**
     * Returns a task based on the specified task number.
     *
     * @param taskNumber Task to be added
     * @return Task of the specified number
     */
    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Returns a new task list with marked task.
     *
     * @param numberToMark Integer of task to be marked.
     * @return Task list updated with marked task
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
     * Returns a new task list with unmarked task.
     *
     * @param numberToUnmark Integer of task to be unmarked.
     * @return Task list updated with unmarked task
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
     * Returns the size of the list.
     *
     * @return Size of task list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns true if task list is empty.
     *
     * @return True if length of task list is 0, otherwise false
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the list of tasks to be done.
     *
     * @return List of tasks to be done
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
