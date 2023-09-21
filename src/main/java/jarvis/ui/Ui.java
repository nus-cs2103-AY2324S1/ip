package jarvis.ui;

import jarvis.task.Task;
import jarvis.tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface for interacting with the user.
 * The class is responsible for displaying messages to the user and reading user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes the Ui class and prepares it to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the provided texts to the user surrounded by horizontal lines.
     *
     * @param text The messages to be displayed to the user.
     */
    public void display(String... text) {
        System.out.println("____________________________________________________________");
        for (String i : text)
            System.out.println(i);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        display("Hello Master! I'm Jarvis, your AI personal assistant.", "What can I do for you?");
    }

    /**
     * Displays a farewell message to the user.
     */
    public void farewell() {
        display("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that has been added.
     * @param tasks The current task list.
     */
    public void displayAddedTask(Task task, TaskList tasks) {
        display("Got it. I've added this task:", task.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that has been deleted.
     * @param tasks The current task list.
     */
    public void displayDeletedTask(Task task, TaskList tasks) {
        display("Got it. I've removed this task:", task.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that has been marked.
     */
    public void displayMarkedTask(Task task) {
        display("Nice! I've marked this task as done:", task.toString());
    }

    /**
     * Displays a message confirming that a task has been unmarked.
     *
     * @param task The task that has been unmarked.
     */
    public void displayUnmarkedTask(Task task) {
        display("OK, I've marked this task as not done yet:", task.toString());
    }

    /**
     * Displays a message informing the user that their task list is currently empty.
     */
    public void displayEmptyList() {
        display("You currently have no tasks in your list.");
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays all the tasks in the user's task list.
     *
     * @param tasks The user's current task list.
     */
    public void displayList(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays tasks that match a search query.
     *
     * @param tasks The list of tasks that match the search query.
     */
    public void displayMatchingTasks(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("No tasks matched your search query.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }
}
