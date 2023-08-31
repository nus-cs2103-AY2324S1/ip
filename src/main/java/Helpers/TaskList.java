package Helpers;

import Tasks.Task;

import java.util.ArrayList;

/**
 * Represents TaskList class that contains the list of tasks as well as CRUD operations
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Empty constructor for no tasks input
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor with tasks in input
     * @param tasks list of tasks to initialise
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task markTaskAsDone(int index) {
        Task t = tasks.get(index);
        t.markedAsDone();
        return t;
    }

    public Task markTaskAsUnDone(int index) {
        Task t = tasks.get(index);
        t.markedAsUndone();
        return t;
    }

    public int getListLength() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public String printTaskList() {
        if (this.tasks.isEmpty()) {
            return "No tasks recorded, macho!";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the list of tasks recorded: \n");
        for (int i = 0; i < this.tasks.size(); i++) {
            int index = i + 1;
            Task task = this.tasks.get(i);
            sb.append(index).append(".").append(task.toString()).append("\n");
        }
        return sb.toString().trim();
    }

}
