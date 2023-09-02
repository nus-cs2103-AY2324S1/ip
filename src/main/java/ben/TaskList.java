package ben;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /**
     * Arraylist of tasks
     */
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds task to the taskList and displays a message to the user if the isDisplayMessage is true.
     *
     * @param task The task to be added to the task list.
     * @param isDisplayMessage Whether to display the message of the task being added.
     */
    public void add(Task task, Boolean isDisplayMessage) {
        tasks.add(task);
        if (isDisplayMessage) {
            addSuccessMessage(task);
        }
    }

    /**
     * Displays a message to the user that the task has been added to the task list.
     *
     * @param task The task that has been added.
     */
    public void addSuccessMessage(Task task) {
        Ui.displayMessage("\nGot It! This task has been added:\n" + task +
                "\nNow you have " + tasks.size() + " items in the list\n");
    }

    /**
     * Deletes task from the task list.
     *
     * @param task The task to be deleted.
     */
    public void delete(Task task) {
        tasks.remove(task);
        Ui.displayMessage("\n" + "Sure thing! This task has been removed\n" + task +
                "\nNow you have " + tasks.size() + " tasks left\n");
    }

    /**
     * String representation of the tasks in the file.
     *
     * @return String representation of the tasks in the file.
     */
    public String saveTasks() {
        StringBuilder s = new StringBuilder();
        for (Task task : tasks) {
            s.append(task.saveString()).append("\n");
        }
        return s.toString();
    }

    /**
     * Number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the specified task in the task list.
     *
     * @param index Index of the task.
     * @return The specified task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * String representation of the task.
     *
     * @return String representation of the task..
     */
    @Override
    public String toString() {
        String message = "";
        for (int i = 1; i <= tasks.size(); i++) {
            message += i + ". " + tasks.get(i - 1).toString() + "\n";
        }
        return message;
    }
}
