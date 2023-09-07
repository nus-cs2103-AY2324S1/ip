package task;

import java.util.ArrayList;

/**
 * Represents a list of task to do
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * returns the length of to do list
     *
     * @return length of to do list
     */
    public int getSize() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.getSize() == 0;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds the given task to the list
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the given task from the list
     */
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Marks the given task as done
     */
    public void markTask(Task task) {
        task.markAsDone();
    }

    /**
     * Marks the given task as undone
     */
    public void unmarkTask(Task task) {
        task.markAsUndone();
    }

    /**
     * Returns a string representation of the task list
     */
    public String toString() {
        int index = 1;
        String res = "";
        for (Task task : tasks) {
            res += String.format("%d. %s\n", index, task.toString());
            index++;
        }
        return res;
    }

    /**
     * Filters the task list by the given keyword
     *
     * @param keyword keyword to filter the task list by
     * @return filtered task list with tasks which contain the given keyword
     */
    public ArrayList<Task> filterTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        String keywordLowercase = keyword.toLowerCase();
        for (Task task : tasks) {
            String description = task.getDescription().toLowerCase();
            if (description.contains(keywordLowercase)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
