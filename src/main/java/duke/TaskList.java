package duke;

import java.util.ArrayList;

/**
 * Encapsulates task list in the chat bot.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        Ui.listTasks(this.tasks);
    }

    /**
     * Adds one task to the task list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        Ui.addTask(t, this.tasks);
        this.storage.addTheLastTaskToFile(this.tasks);
    }

    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param num The position starts from 0.
     */
    public void deleteTask(int num) {
        Task re = tasks.remove(num);
        this.storage.rewriteFile(tasks);
        Ui.deleteTask(re, tasks);
    }

    /**
     * Marks the task at the specified position in the task list.
     *
     * @param num The position starts from 0.
     */
    public void markTask(int num) {
        Task t = tasks.get(num);
        t.markAsDone();
        Ui.markTask(t);
        this.storage.rewriteFile(tasks);
    }

    /**
     * Searches all Tasks with the keyword.
     *
     * @param keyword The keyword.
     */
    public void findTask(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                result.add(tasks.get(i));
            }
        }
        Ui.listMatchingTasks(result);
    }
}
