package duke.ui;

import java.util.List;

import duke.task.Task;

/**
 * Represents the user interface of the program.
 */
public class Ui {

    private final String name;
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Prints the welcome message when the program starts.
     */
    public void showHelloMessage() {
        showDottedLine();
        System.out.println("Hello! I'm Snake CYQJ");
        System.out.println("What can I do for you?");
        showDottedLine();
    }

    /**
     * Prints dotted line.
     */
    public static void showDottedLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the goodbye message.
     */
    public static void showGoodbyeMessage() {
        Ui.showDottedLine();
        System.out.println("Bye. Hope to see you again soon!");
        Ui.showDottedLine();
    }

    /**
     * Prints the delete message.
     * @param tasks the list of tasks
     * @param index the index of the task to be deleted
     */
    public static void showDeleteTaskMessage(List<Task> tasks, int index) {
        showDottedLine();
        System.out.println("Noted. I've removed this duke.task:");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        System.out.println("Now you have " + tasks.size() + " " + placeholder + " in the list.");
        showDottedLine();
    }

    /**
     * Prints the add task message.
     * @param tasks the list of tasks
     */
    public static void showAddTaskMessage(List<Task> tasks) {
        showDottedLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        System.out.println("Now you have " + tasks.size() + " " + placeholder + " in the list.");
        showDottedLine();
    }

    /**
     * Prints the list tasks message.
     * @param tasks the list of tasks
     */
    public static void showListTasksMessage(List<Task> tasks) {
        showDottedLine();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        showDottedLine();
    }

    /**
     * Prints the done task message.
     * @param tasks the list of tasks
     * @param index the index of the completed task
     */
    public static void showMarkAsDoneMessage(List<Task> tasks, int index) {
        showDottedLine();
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(index - 1).markAsDone();
        System.out.println(tasks.get(index - 1));
        showDottedLine();
    }

    /**
     * Prints the undone task message.
     * @param tasks the list of tasks
     * @param index the index of the uncompleted task
     */
    public static void showMarkAsUndoneMessage(List<Task> tasks, int index) {
        showDottedLine();
        System.out.println("OK, I've marked this task as not done yet:");
        tasks.get(index - 1).markAsUndone();
        System.out.println(tasks.get(index - 1));
        showDottedLine();
    }

    /**
     * Shows the error message when saving file.
     */
    public static void showErrorSavingToFileMessage() {
        System.out.println("Error saving data to file.");
    }

    /**
     * Shows the error message when loading file.
     */
    public static void showErrorLoadingFromFileMessage() {
        System.out.println("Error loading data from file.");
    }


}
