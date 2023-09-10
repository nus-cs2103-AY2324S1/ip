package buddy;

import java.util.ArrayList;

import buddy.utils.BuddyException;

/**
 * The TaskList class contains the task list and its related operations.
 * @author Lim Jin Yin
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private final String filePath = "./data/tasks.txt";

    /**
     * The constructor for an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * The constructor for a (non-empty) TaskList.
     *
     * @param tasks The arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the specific task in this TaskList.
     *
     * @param taskIndex The zero-based index of the task.
     * @return Returns the task with the specified index.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Returns the list of tasks in this TaskList.
     *
     * @return Returns an arraylist of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in this TaskList.
     * @return Returns the size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a new task of type Task to this TaskList.
     * @param task The task that is added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the specified task from the task list.
     * @param taskIndex The zero-based index of the task.
     * @throws BuddyException If taskIndex is invalid.
     */
    public void deleteTask(int taskIndex) throws BuddyException {
        if (tasks.size() == 0 ) {
            System.out.println("There are no tasks in your list to delete.");
        } else if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    /**
     * Marks the specified task in the task list as done.
     * @param taskIndex The zero-based index of the task.
     * @throws BuddyException If taskIndex is invalid.
     */
    public void markAsDone(int taskIndex) throws BuddyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markTaskAsDone();
            // System.out.println("Nice! I've marked this task as done:");
            // System.out.println(tasks.get(taskIndex).toString());
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    /**
     * Marks the specified task in the task list as not done.
     * @param taskIndex The zero-based index of the task.
     * @throws BuddyException If taskIndex is invalid.
     */
    public void markAsNotDone(int taskIndex) throws BuddyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markTaskAsUndone();
            // System.out.println("OK, I've marked this task as not done yet:");
            // System.out.println(tasks.get(taskIndex).toString());
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    /**
     * Finds tasks in the task list containing the keyword.
     *
     * @param keyword The keyword of the task to be found.
     * @return TaskList containing tasks that contain the specified keyword.
     */
    public TaskList findTask(String keyword) {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                filteredTasks.addTask(tasks.get(i));
            }
        }
        return filteredTasks;
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
