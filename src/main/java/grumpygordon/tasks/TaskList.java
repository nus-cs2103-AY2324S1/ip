package grumpygordon.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks added by the user.
 */
public class TaskList {

    /**
     * Indentation for responses.
     */
    private static final String INDENTATION = "     ";

    /**
     * Response when list is empty.
     */
    private static final String EMPTY_TASK_LIST_RESPONSE = "The list is empty, you donkey!";

    /**
     * Response when list is not empty.
     */
    private static final String NON_EMPTY_TASK_LIST_RESPONSE = "Here's your list, you donkey!";

    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructor of TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the info of a tasks from it's index in the list.
     * @param index Index of the task in the list
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task to the list.
     * @param task Task to be added
     */
    public void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        }
    }

    /**
     * Deletes a task from the list.
     * @param index Index of task to be deleted
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }
    /**
     * Sorts the tasks in the list.
     */
    public void sortTasks() {
        this.tasks.sort(new TaskComparator());
    }

    /**
     * Marks a task as done.
     * @param index Index of task to be marked
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a task as undone.
     * @param index Index of task to be marked
     */
    public void markTaskAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
    }

    /**
     * Returns a String representation of a TaskList.
     * @return String representation of a TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.tasks.size() == 0) {
            sb.append(INDENTATION)
                    .append(EMPTY_TASK_LIST_RESPONSE)
                    .append("\n");
        } else {
            sb.append(INDENTATION).append(NON_EMPTY_TASK_LIST_RESPONSE).append("\n");
            for (int i = 0; i < this.tasks.size(); i++) {
                sb.append(INDENTATION)
                        .append(i + 1)
                        .append(".")
                        .append(tasks.get(i).toString())
                        .append("\n");
            }
        }

        return sb.toString();
    }
}
