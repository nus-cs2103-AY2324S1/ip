package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;
import duke.task.Task;

/**
 * The Ui class handles the user interface interactions of the Duke application.
 */
public class Ui {
    private Scanner scanner;
    private static final String DIVIDER = "___________________________________\n";

    /**
     * Constructs an Ui object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a farewell message to the user upon exiting the application.
     */
    public void showExit() {
        System.out.println("Bye Bye. Hope to see you again soon!\n");
    }

    /**
     * Displays a welcome message along with the existing task list or an empty list message.
     *
     * @param list The list of tasks.
     */
    public void showWelcome(ArrayList<Task> list) {
        String msg = "Looks like you have been here before!\n";
        if (list.isEmpty()) {
            msg = "Looks like your list is empty!\nTime to add some new Tasks!\n";
        }
        String welcome = String.format("Hi I'm Duke but BETTTERRRR!!!\n%s", msg);
        System.out.println(welcome + DIVIDER);
        if (!list.isEmpty()){
            showList(list);
            showDivider();
        }
    }

    /**
     * Displays the standard divider line.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Displays a message confirming the deletion of a task and the updated task list size.
     *
     * @param task The task that was deleted.
     * @param list The updated list of tasks.
     */
    public void showDelete(Task task, ArrayList<Task> list) {
        String msg = String.format("Okay I have deleted this task from the list\n\t %s\n" +
                "Now you have %d items in your list\n", task.toString(), list.size());
        System.out.println(msg);
    }

    /**
     * Displays a message confirming the addition of a new task and the updated task list size.
     *
     * @param task The task that was added.
     * @param list The updated list of tasks.
     */
    public void showTaskAdded(Task task, ArrayList<Task> list) {
        String msg = String.format("Okay!! I have added a new %s\n\t %s\n" +
                "You now have %d items in your list!", task.getType(), task.toString(), list.size());
        System.out.println(msg);
    }

    /**
     * Displays a message confirming the marking or unMarking of a task as done.
     *
     * @param type The type of action ("mark" or "unMark").
     * @param task The task that was marked or unmarked.
     */
    public void showMark(String type, Task task) {
        String msg = String.format("Okay I have unmarked this task:\n%s\n", task.toString());
        if (type.equals("mark")) {
            msg = String.format("Nice!! I have marked this task as done:\n%s\n", task.toString());
        }
        System.out.println(msg);
    }

    /**
     * Displays the list of tasks with their respective indices.
     *
     * @param list The list of tasks to be displayed.
     */
    public void showList(ArrayList<Task> list) {
        Task[] temp = list.toArray(new Task[0]);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < temp.length; i++) {
            System.out.println(i + 1 + ". " + temp[i].toString());
        }
    }


    /**
     * Displays a list of tasks that match the specified search criteria.
     *
     * This method is used to show a subset of tasks from the given list that match a certain search criteria.
     * It displays the matching tasks along with their indices in the list.
     *
     * @param list The list of tasks to search within.
     */
    public void showListMatching(ArrayList<Task> list) {
        Task[] temp = list.toArray(new Task[0]);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < temp.length; i++) {
            System.out.println(i + 1 + ". " + temp[i].toString());
        }
    }


    /**
     * Reads and retrieves a user command from the console input.
     *
     * @return The user command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an "invalid command" message to the user.
     */
    public void invalidCommand() {
        System.out.println(DIVIDER + "Oops! That does not seem to be a valid action!\n" + DIVIDER);
    }
}
