package sisyphus.task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskList;

    /**
     * Constructor to initialise empty ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns number of items in the taskList.
     *
     * @return number of items
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds the given task into the tasklist
     *
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Gets the task based on the index.
     *
     * @param index
     * @return the task at the given index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Mark the task at the given index as done.
     *
     * @param index
     */
    public void markTask(int index) {
        taskList.get(index).setDone(true);
    }

    /**
     * Mark the task at the given index as undone.
     *
     * @param index
     */
    public void unmarkTask(int index) {
        taskList.get(index).setDone(false);
    }

    /**
     * Delete the task at the given index.
     *
     * @param index
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Return the task at the last index.
     *
     * @return the last task added into the list.
     */
    public Task getLastTask() {
        return getTask(getSize()- 1);
    }

    /**
     * Searches through tasks for a taskList with description containing keyword.
     *
     * @param keyword
     * @return taskList with only tasks containing then keyword.
     */
    public TaskList findMatchingTasks(String keyword) {
        TaskList matchingTaskList = new TaskList();
        for (int i = 0; i < getSize(); i++) {
            if (this.getTask(i).hasKeyword(keyword)) {
                matchingTaskList.addTask(this.getTask(i));
            }
        }
        return matchingTaskList;
    }
}
