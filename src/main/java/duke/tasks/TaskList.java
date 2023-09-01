package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task markDone(int index) {
        Task task = tasks.get(index);
        task.markDone();
        return task;
    }

    public Task unmarkDone(int index) {
        Task task = tasks.get(index);
        task.unmarkDone();
        return task;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns an ArrayList of tasks that match the keyword.
     *
     * @param keyword The keyword to search for.
     * @return An ArrayList of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
