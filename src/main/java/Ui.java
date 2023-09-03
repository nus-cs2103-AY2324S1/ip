import Tasks.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui handles the interactions with user.
 *
 * @author Sebastian Tay
 */
public class Ui {
    private Scanner sc;

    public Ui(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Retrieves input from the user in the command line.
     *
     * @return
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Greets the user.
     */
    public void welcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Veda initialised. How may I help you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Terminates the ui and closes the scanner.
     */
    public void exit() {
        System.out.println("Terminating reader.");
        sc.close();
        System.out.println("Bye. All the best for your mission!");
    }

    /**
     * Informs user that command is unrecognised.
     */
    public void displayUnrecognisedInput() {
        System.out.println("Unrecognised command.");
    }


    /**
     * List out the missions in tasks.
     *
     * @param tasks contains the missions to be listed.
     */
    public void displayList(ArrayList<Task> tasks) {
        System.out.println("Missions:");

        tasks.forEach( task -> System.out.println(
                (tasks.indexOf(task) + 1) + "." + task
        ));

    }

    /**
     * List out the missions in tasks with the message at the top.
     *
     * @param tasks
     * @param message
     */
    public void displayList(ArrayList<Task> tasks, String message) {
        System.out.println(message);

        tasks.forEach( task -> System.out.println(
                (tasks.indexOf(task) + 1) + "." + task
        ));

    }
}
