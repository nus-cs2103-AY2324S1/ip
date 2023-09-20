package duke;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * The TaskList class represents a list of tasks. It provides methods for adding, listing, deleting, and
 * marking tasks as done or undone. TaskList can also be serialized to save and load tasks from a file.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> taskArrayList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        taskArrayList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskArrayList = tasks;
    }

    /**
     * Lists all tasks in the TaskList, displaying their descriptions and statuses.
     */
    public void listTasks() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            System.out.println((i + 1) + ". " + task);
        }
    }

    /**
     * Deletes a task from the TaskList by its ID.
     *
     * @param id The ID of the task to be deleted.
     * @return A message indicating the result of the deletion.
     */
    public String deleteTask(int id) {
        if (id >= 1 && id <= taskArrayList.size()) {
            Task task = taskArrayList.remove(id - 1);
            return "Noted. I've removed this task:\n" + task
                    + "\nNow you have " + taskArrayList.size() + " tasks in the list.";
        } else {
            return "Invalid task number. Please enter a valid task number.";
        }
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     * @return A message indicating the result of the addition.
     */
    public String addTask(Task task) {
        taskArrayList.add(task);
        return "Got it. I've added this task:\n" + taskArrayList.get(taskArrayList.size() - 1)
                + "\nNow you have " + taskArrayList.size() + " tasks in the list.";
    }

    /**
     * Marks a task as done by its ID.
     *
     * @param id The ID of the task to be marked as done.
     * @return A message indicating the result of marking the task as done.
     */
    public String markTaskAsDone(int id) {
        if (id >= 1 && id <= taskArrayList.size()) {
            Task task = taskArrayList.get(id - 1);
            if (!task.isDone) {
                task.markAsDone();
                return "Nice! I've marked this task as done:\n" + task;
            } else {
                return "This task is already marked as done:\n" + task;
            }
        } else {
            return "Invalid task number. Please enter a valid task number.";
        }
    }

    /**
     * Marks a task as undone (not done) by its ID.
     *
     * @param id The ID of the task to be marked as undone.
     * @return A message indicating the result of marking the task as undone.
     */
    public String unmarkTask(int id) {
        if (id >= 1 && id <= taskArrayList.size()) {
            Task task = taskArrayList.get(id - 1);
            if (task.isDone) {
                task.markAsUndone();
                return "OK, I've marked this task as not done yet:\n" + task;
            } else {
                return "This task is already marked as not done:\n" + task;
            }
        } else {
            return "Invalid task number. Please enter a valid task number.";
        }
    }

    /**
     * Retrieves the ArrayList of tasks contained in the TaskList.
     *
     * @return The ArrayList of tasks.
     */
    protected ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    /**
     * Lists tasks in the TaskList whose description matches with keyword.
     *
     * @param key The keyword users are looking up for.
     */
    public void findTasks(String key) {
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            if(task.description.contains(key)) {
                System.out.println((i + 1) + ". " + task);
            }
        }
    }
}
