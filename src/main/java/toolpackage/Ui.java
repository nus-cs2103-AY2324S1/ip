package toolpackage;

import taskpackage.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the ui of the Duke bot.
 * A Ui object is responsible for printing
 * the relevant information onto the terminal
 * for the user to read.
 */
public class Ui {

    private static final String TEXT_GREETING = "Hello! I'm ChampionSOS\nWhat can I do for you?";
    private static final String TEXT_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String TEXT_NO_DATA = "No existing data found. New file created!";

    public Ui() {
    }

    /**
     * Prints greeting message to user.
     */
    public void showWelcome() {
        System.out.println(Ui.TEXT_GREETING);
    }

    /**
     * Prints goodbye message to user.
     */
    public void showBye() {
        System.out.println(Ui.TEXT_GOODBYE);
    }

    /**
     * Prints "No existing data" message to user.
     */
    public void showLoadingError() {
        System.out.println(Ui.TEXT_NO_DATA);
    }

    /**
     * Reads and returns the inputs by users.
     *
     * @param inputs Commands given by user.
     * @return String
     */
    public String readCommands(Scanner inputs) {
        return inputs.nextLine();
    }

    /**
     * Prints list of tasks.
     *
     * @param listOfTasks List of tasks to print.
     * @return String Representation of tasks.
     */
    public String printList(ArrayList<Task> listOfTasks) {
        String listToPrint = "Here are the tasks in your list:\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            listToPrint+= String.format("%d.%s%n", i + 1, listOfTasks.get(i).printTask());
        }
        return listToPrint;
    }

    /**
     * Prints the toggling status of the task.
     *
     * @param task Task that was marked or unmarked.
     * @param keyword Word to indicate whether the task was marked as complete or incomplete.
     * @return String Reply to user that task has been marked as complete or incomplete.
     */
    public String toggleDone(Task task, String keyword) {
        if (keyword.equals("mark")) {
            return String.format("Nice! I've marked this task as done:%n %s%n", task.printTask());
        } else {
            return String.format("OK, I've marked this task as not done yet:%n %s%n", task.printTask());
        }
    }

    /**
     * Prints the deletion status of the task.
     *
     * @param task Task that was deleted.
     * @param size Updated number of tasks in the list.
     * @return String Reply to user that task has been deleted.
     */
    public String removeItem(Task task, int size) {
        return String.format("Noted. I've removed this task:%n %s%nNow you have %d tasks in the list.%n",
                task.printTask(), size);
    }

    /**
     * Prints the addition status of the task.
     *
     * @param task Task that was added.
     * @param size Updated number of tasks in the list.
     * @return String Reply to user on task that was added.
     */
    public String addItem(Task task, int size) {
        return String.format("Got it. I've added this task:%n %s%nNow you have %d tasks in the list.%n",
                task.printTask(), size);
    }

    /**
     * Prints out the list of tasks that contain the given keyword.
     *
     * @param listOfTasks List of tasks to print.
     * @return String Representation of list of tasks to print.
     */
    public String printMatchingTasks(ArrayList<Task> listOfTasks) {
        String matchingTasks = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            matchingTasks += String.format("%d.%s%n", i + 1, listOfTasks.get(i).printTask());
        }
        return matchingTasks;
    }
}
