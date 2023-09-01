package noac;

import noac.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * For interactions with the user such as printing or getting the input.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Create the UI object and initialise the scanner.
     */
    public Ui(){
        scanner = new Scanner(System.in);
    }

    /**
     * Print the welcome message.
     */
    public void showWelcomeMessage() {
        String logo =  " _   _  ___    _    ____\n" +
                "| \\ | |/ _ \\  / \\  / ___|\n" +
                "|  \\| | | | |/ _ \\| |\n" +
                "| |\\  | |_| / ___ \\ |___\n" +
                "|_| \\_|\\___/_/   \\_\\____|\n";
        System.out.println("Hello from\n" + logo);

        String welcomeMessage = "    ____________________________________________________________\n" +
                "     Hello! I'm NOAC\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(welcomeMessage);

    }


    /**
     * Print the bye message.
     */
    public void showByeMessage() {
        String byeMessage = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";

        System.out.println(byeMessage);
    }

    /**
     * Prints the error message.
     *
     * @param e The error which message is to be printed.
     */
    public void showErrorMessage(NoacException e) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + e.getMessage());
        System.out.println("    ____________________________________________________________");
    }


    /**
     * Prints all the tasks in the list.
     *
     * @param taskList The list to be printed.
     */
    public void showList(TaskList taskList) {

        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println("     " + i + "." + taskList.getTask(i-1).toString());
        }
        System.out.println("    ____________________________________________________________");

    }

    /**
     * Lets the user know the task has been mark/unmark.
     *
     * @param task The task to be mark/unmark.
     * @param isMark Boolean to determine whether to mark or unmark.
     */
    public void showMarkOrUnmark(Task task, boolean isMark) {

        System.out.println("    ____________________________________________________________");

        if (isMark) {
            System.out.println("     Nice! I've marked this task as done:");

        } else {
            System.out.println("     OK, I've marked this task as not done yet:");
        }

        System.out.println("       " + task.toString());
        System.out.println("    ____________________________________________________________");
    }


    /**
     * Lets the user know the task has been added.
     *
     * @param task The task that was added.
     * @param listSize The number of task in the list.
     */
    public void showAddTask(Task task, int listSize) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + listSize + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }


    /**
     * Lets the user know the task has been deleted.
     *
     * @param task The task that was added.
     * @param listSize The number of task in the list.
     */
    public void showDeleteTask(Task task, int listSize) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task.toString());

        System.out.println("     Now you have " + listSize + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }


    /**
     * Shows the task on that date.
     *
     * @param tasks The task to be displayed.
     */
    public void showOnDate(ArrayList<Task> tasks) {

        System.out.println("    ____________________________________________________________");
        System.out.println("     The tasks on this date are:");

        for(int i = 0 ; i < tasks.size(); i++) {
            System.out.println("     " + tasks.get(i).toString());
        }

        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints all the tasks that matched a word.
     *
     * @param tasks The task to be printed.
     */
    public void showFind(ArrayList<Task> tasks) {

        System.out.println("    ____________________________________________________________");
        if (tasks.size() == 0) {
            System.out.println("     There are no matching tasks in your list. :(");
        } else {
            System.out.println("     Here are the matching tasks in your list:");

            for(int i = 0 ; i < tasks.size(); i++) {
                System.out.println("     " + tasks.get(i).toString());
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Gets the user input.
     *
     * @return The user input.
     */
    public String readCommand(){
        return scanner.nextLine();
    }
}
