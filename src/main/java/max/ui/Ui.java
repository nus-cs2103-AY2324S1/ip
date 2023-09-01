package max.ui;

import max.tasks.Task;
import java.util.List;
import java.util.Scanner;

/**
 * Handles interactions with user.
 */
public class Ui {
    static String line = "____________________________________________________________";

    /**
     * Initialises UI.
     */
    public Ui() {
    }

    /**
     * Prints greeting message.
     */
    public void showGreeting() {
        System.out.println("     Hello from");
        System.out.println("       /\\/\\   __ ___  __");
        System.out.println("      /    \\ / _` \\ \\/ /");
        System.out.println("     / /\\/\\ \\ (_| |>  <");
        System.out.println("     \\/    \\/\\__,_/_/\\_\\");
        System.out.println("     How may I assist you?");
        System.out.println(line);
    }

    /**
     * Opens a scanner to read user input.
     *
     * @return the user input.
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Prints exit message.
     */
    public void exit() {
        System.out.println("     Bye! Please come again!");
    }

    /**
     * Prints line divider.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Prints error message when no tasks found.
     */
    public void showLoadingError() {
        System.out.println("No tasks were found! Making you a new list real quick xd");
    }

    /**
     * Prints message after task marked.
     *
     * @param task task that was marked.
     */
    public void showMark(Task task) {
        System.out.println("     Good job on completing your task!");
        System.out.println("       " + task);
    }

    /**
     * Prints message after task unmarked.
     *
     * @param task task that was unmarked.
     */
    public void showUnmark(Task task) {
        System.out.println("     Okay, I've marked this as not done yet!");
        System.out.println("       " + task);
    }

    /**
     * Prints message after task added.
     *
     * @param task task that was added.
     * @param size number of tasks in list.
     */
    public void showAdd(Task task, int size) {
        System.out.println("     I gotchu. I've added this task:");
        System.out.println("       " + task);
        System.out.println(String.format("     Now you have %d task(s) in the list.", size));
    }

    /**
     * Prints message after task deleted.
     *
     * @param task task that was deleted.
     * @param size number of tasks in list.
     */
    public void showDelete(Task task, int size) {
        System.out.println("     Aights mate. I've killed this task:");
        System.out.println("       " + task);
        System.out.println(String.format("     Now you have %d task(s) left.", size));
    }

    /**
     * Prints list of all tasks.
     *
     * @param list List containing tasks
     */
    public void showList(List list) {
        if (list.isEmpty()) {
            System.out.println("     Hohoho, you have no tasks.");
        } else {
            System.out.println("     Here are all your tasks:");

            // Iterate through ArrayList of tasks and enumerate them
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                System.out.println("     " + index + ". " + list.get(i));
            }
        }
    }

    /**
     * Prints error message.
     *
     * @param msg Error message.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }
}
