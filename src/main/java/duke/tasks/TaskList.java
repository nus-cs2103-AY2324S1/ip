package duke.tasks;

import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Duke application.
 */
public class TaskList {

    /** The list of tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list and prints a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        Ui.printMessageWithSeparator("Got it. I've added this task:\n" + task.getDescription()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the task list and prints a confirmation message.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            Ui.printMessageWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index);
            tasks.remove(index);
            Ui.printMessageWithSeparator("Noted. I've removed this task:\n" + task.getDescription()
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
        }
    }

    /**
     * Marks a task as done and prints a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            Ui.printMessageWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index);
            task.markAsDone();
            Ui.printMessageWithSeparator("Nice! I've marked this task as done:\n" + task.getDescription());
        }
    }

    /**
     * Marks a task as not done and prints a confirmation message.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unmarkTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            Ui.printMessageWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index);
            task.unmark();
            Ui.printMessageWithSeparator("OK, I've marked this task as not done yet:\n" + task.getDescription());
        }
    }

    /**
     * Prints all the tasks in the task list.
     */
    public void list() {
        System.out.println(Ui.LINE_SEPARATOR);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getDescription());
        }
        System.out.println(Ui.LINE_SEPARATOR);
    }

    /**
     * Finds tasks that contain a given keyword and prints them.
     *
     * @param keyword The keyword to be searched for.
     */
    public void findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() == 0) {
            Ui.printMessageWithSeparator("No matching tasks found.");
        } else {
            System.out.println(Ui.LINE_SEPARATOR);
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                Task task = foundTasks.get(i);
                System.out.println((i + 1) + ". " + task.getDescription());
            }
            System.out.println(Ui.LINE_SEPARATOR);
        }
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks in an ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}

