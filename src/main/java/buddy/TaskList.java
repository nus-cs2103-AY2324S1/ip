package buddy;

import java.util.ArrayList;

import buddy.utils.BuddyException;

public class TaskList {
    private ArrayList<Task> tasks;
    private final String filePath = "./data/tasks.txt";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) throws BuddyException {
        if (tasks.size() == 0 ) {
            System.out.println("There are no tasks in your list to delete.");
        } else if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    public void markAsDone(int taskIndex) throws BuddyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markTaskAsDone();
            // System.out.println("Nice! I've marked this task as done:");
            // System.out.println(tasks.get(taskIndex).toString());
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    public void markAsNotDone(int taskIndex) throws BuddyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markTaskAsUndone();
            // System.out.println("OK, I've marked this task as not done yet:");
            // System.out.println(tasks.get(taskIndex).toString());
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!tasks.isEmpty()) {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i));
                sb.append("\n");
            }
        } else {
            sb.append("There are no tasks in your list.\n");
        }
        return sb.toString();
    }
}
