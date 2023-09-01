package chatbot.task;

import java.util.ArrayList;
import java.util.List;

/**
 * List of task.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for this class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add task into the list.
     *
     * @param task Task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Get the task in the list.
     * @param index number of the task
     * @return the task
     */
    public Task getTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            return tasks.get(index - 1);
        }
        return null;
    }

    /**
     * Provide the size of the list.
     *
     * @return size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * mark the task which is in the list as done.
     *
     * @param index the index of the task in the list
     */
    public void markTaskDone(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.markAsDone();
        }
    }

    /**
     *  unmark the task which is in the list as not done.
     *
     * @param index the index of the task in the list
     */
    public void unMarkTask(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.unMark();
        }
    }

    /**
     * print the task
     */
    public void printTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, tasks.get(i).toString());
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Remove the task from the list.
     *
     * @param index index of the task
     * @return the task being removed
     */
    public Task removeTask(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * get the whole list of task
     *
     * @return List</Task> of the task
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }

}
