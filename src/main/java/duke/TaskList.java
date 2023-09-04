package duke;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks and provides methods to manipulate the list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList object with overloading.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list and displays a confirmation message.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        tasks.add(task);
        System.out.println("Now you have " + getSize() + " task(s) in the list.");
    }

    /**
     * Removes a task from the task list by its index and displays a confirmation message.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index).toString());
        tasks.remove(index);
        System.out.println("Now you have " + getSize() + " task(s) in the list.");
    }

    /**
     * Marks a task as done by its index and displays a confirmation message.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println("Now you have " + getSize() + " task(s) in the list.");
    }

    /**
     * Marks a task as not done by its index and displays a confirmation message.
     *
     * @param taskIndex The index of the task to be marked as not done.
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println("Now you have " + getSize() + " task(s) in the list.");
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the task list, including task numbers.
     *
     * @return A formatted string displaying all tasks in the list with task numbers.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    public ArrayList<Task> findTasksWithKeyword(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}