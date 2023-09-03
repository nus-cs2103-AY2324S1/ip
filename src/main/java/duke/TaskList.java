package duke;

import java.util.ArrayList;

/**
 * Represents the task list.
 */
public class TaskList {

    private ArrayList<Task> storedTasks;

    /**
     * Constructor to initialise an empty TaskList object.
     */
    public TaskList() {
        this.storedTasks = new ArrayList<>();
    }

    /**
     * Constructor to initialise a TaskList object with stored tasks.
     *
     * @param storedTasks Array of Tasks to be stored in the TaskList.
     */
    public TaskList(ArrayList<Task> storedTasks) {
        this.storedTasks = storedTasks;
    }

    /**
     * Returns the task at the given index in the TaskList.
     *
     * @param i Index of the task.
     */
    public Task getTask(int i) {
        return this.storedTasks.get(i);
    }

    /**
     * Returns the length of the TaskList.
     */
    public int getLength() {
        return this.storedTasks.size();
    }

    /**
     * Adds a task into the TaskList.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        storedTasks.add(t);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        storedTasks.remove(index);
    }

    /**
     * Filters the task list for tasks containing the given keyword.
     *
     * @param keyword Keyword to be searched and filtered for.
     */
    public TaskList getTaskListWithKeyword(String keyword) {
        TaskList list = new TaskList();
        int len = this.getLength();

        for (int i = 0; i < len; i++) {
            Task t = this.getTask(i);
            String desc = t.getDescription();
            int index = desc.indexOf(keyword);

            if (index != -1) {
                list.addTask(t);
            }
        }

        return list;
    }
}
