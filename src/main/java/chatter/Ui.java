package chatter;

import java.util.Scanner;

import chatter.task.Task;

/**
 * Represents a UI class to handle user interactions.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Ui {
    private static final String DIVIDER = "-----------------------";
    /** Scanner object that takes in user inputs. */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a welcome message when user starts the chatbot.
     */
    public void showWelcome() {
        System.out.println(DIVIDER + "\nHello! I'm chatter.Chatter!" + "\nHow can i help you today?\n" + DIVIDER);
    }

    /**
     * Prints a divider.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints a welcome message when user exits the chatbot.
     */
    public void showExit() {
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a welcome message when user exits the chatbot.
     */
    public void showLoadingError() {
        System.out.println(DIVIDER + "\nError reading data from file!\n" + DIVIDER);
    }

    /**
     * Reads the user input and returns a string of the raw user input.
     *
     * @return A string of the raw user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints task string when task is added to the list.
     *
     * @param task chatter.task.Task object being added to the list.
     */
    public void showAddedTask(Task task, int numOfTasks) {
        System.out.println("Got it. I have added this task to do:");
        System.out.println("  " + task.toString());
        System.out.println("You now have " + numOfTasks + " task(s) in the list.");
    }

    /**
     * Prints completed task string.
     *
     * @param task chatter.task.Task that is being marked done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Good job! I've marked this task as completed:");
        System.out.println("  " + task);
    }

    /**
     * Prints unmarked task string.
     *
     * @param task chatter.task.Task that is being unmarked.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Prints out deleted task message.
     *
     * @param task Task to be deleted.
     * @param numOfTasks Number of tasks left in the list after deletion.
     */
    public void showDeletedTask(Task task, int numOfTasks) {
        System.out.println("Noted! I have removed this task:");
        System.out.println("  " + task);
        System.out.println("You now have " + numOfTasks + " task(s) in the list.");
    }

    /**
     * Prints out list of tasks to display to the user.
     */
    public void showListTasks(TaskList tasks, int numOfTasks) {
        System.out.println("These are all the task(s) in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println("  " + (i + 1) + "." + tasks.getTask(i).toString());
        }
    }

    /**
     * Prints out list of tasks with keyword to display to the user.
     */
    public void showFoundTasks(TaskList tasks, int numOfTasks, String keyword) {
        int count = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            if (tasks.getTask(i).toString().contains(keyword)) {
                System.out.println("  " + count + "." + tasks.getTask(i).toString());
                count++;
            }
        }
    }
}
