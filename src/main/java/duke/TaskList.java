package duke;

import java.util.ArrayList;

/**
 * Encapsulates task list in the chat bot.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs an empty Tasklist with specified Storage.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Constructs a Tasklist with specified tasks and Storage.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return The message for listing.
     */
    public String listTasks() {
        return Ui.listTasks(this.tasks);
    }

    /**
     * Adds one task to the task list.
     *
     * @param t The task to be added.
     * @return The message for adding.
     */
    public String addTask(Task t) {
        this.tasks.add(t);
        this.storage.addTheLastTaskToFile(this.tasks);
        return Ui.addTask(t, this.tasks);
    }

    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param num The position starts from 0.
     * @return The message for deleting.
     */
    public String deleteTask(int num) {
        assert num >= 0;

        Task re = tasks.remove(num);
        this.storage.rewriteFile(tasks);
        return Ui.deleteTask(re, tasks);
    }

    /**
     * Marks the task at the specified position in the task list.
     *
     * @param num The position starts from 0.
     * @return The message for marking.
     */
    public String markTask(int num) {
        assert num >= 0;

        Task t = tasks.get(num);
        t.markAsDone();
        this.storage.rewriteFile(tasks);
        return Ui.markTask(t);
    }

    /**
     * Searches all Tasks with the keyword.
     *
     * @param keyword The keyword.
     * @return The message for searching results.
     */
    public String findTask(String keyword) {
        assert keyword != null;

        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (!tasks.get(i).toString().contains(keyword)) {
                continue;
            }
            result.add(tasks.get(i));
        }
        return Ui.listMatchingTasks(result);
    }

}
