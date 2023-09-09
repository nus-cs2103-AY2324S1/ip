package duke;

import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private final List<Task> tasks;

<<<<<<< HEAD
=======
    /**
     * Constructs an empty TaskList.
     */
>>>>>>> branch-A-JavaDoc
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

<<<<<<< HEAD
    public TaskList (List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask (Task task) {
        tasks.add(task);
    }

=======
    /**
     * Constructs a TaskList with the list of tasks.
     *
     * @param tasks  The list of the tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task  The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param taskIndex  The index of the task.
     */
>>>>>>> branch-A-JavaDoc
    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
        }
    }

<<<<<<< HEAD
=======
    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return  The number of tasks in the TaskList.
     */
>>>>>>> branch-A-JavaDoc
    public int size() {
        return tasks.size();
    }

<<<<<<< HEAD
    public Task getTask (int index) {
        return tasks.get(index);
    }

    public void markTaskAsDone (int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    public void markTaskAsNotDone (int index) {
=======
    /**
     * Gets a task from the TaskList at the specified index.
     *
     * @param index  The index of the task.
     * @return  The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index  The index of the task.
     */
    public void markTaskAsDone(int index) {
    Task task = tasks.get(index);
    task.markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index  The index of the task.
     */
    public void markTaskAsNotDone(int index) {
>>>>>>> branch-A-JavaDoc
        Task task = tasks.get(index);
        task.markAsNotDone();
    }

    /**
     * Finds the tasks that match the keyword.
     *
     * @param keyword  The keywords used to search matching tasks.
     * @return  Tasks that match the keyword.
     */
    public TaskList findTasksByKeyword (String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
}
