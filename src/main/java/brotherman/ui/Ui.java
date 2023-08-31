package brotherman.ui;

import java.util.ArrayList;
import java.util.Scanner;
import brotherman.tasks.*;


/**
 * Represents a user interface to interact with the user
 */
public class Ui {

    /**
     * Scanner to read user input
     */
    private Scanner sc;


    private final String line = "___________________________________________________________\n";


    /**
     * Constructor for Ui
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }


    /**
     * Shows the welcome message to the user
     */
    public void showWelcomeMessage() {
        System.out.println(
                line
                + "Hello! I'm Brotherman\n"
                + "What can I do for you?\n"
                + "Do remember to add your date and time in DD/MM/YYYY\n");
    }


    /**
     * Shows the goodbye message to the user
     */
    public void showGoodbyMessage() {
        System.out.println("Bye, see you again Brotherman!\n");
    }

    /**
     * Shows the error message to the user
     * @param message Error message to be shown to the user
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Shows the error message to the user
     * @param message Error message to be shown to the user
     */
    public String readCommand() {
        String userCommand = sc.nextLine();
        return userCommand;
    }

    /**
     * Shows the error message to the user
     * @param message Error message to be shown to the user
     */
    public void readWrongValue() {
        System.out.println("Brotherman the value you put in wrong.  Try again.");
    }

    /**
     * Shows the error message to the user
     * @param message Error message to be shown to the user
     */
    public void showTaskAdded(TaskList taskList) {
        System.out.println("The task has been added Brotherman \n");
        System.out.println(taskList.list().get(taskList.size() - 1).toString());
        System.out.println("Brotherman you have " + taskList.size() + " tasks in the list!");

    }


    /**
     * Shows the error message to the user
     * @param message Error message to be shown to the user
     */
    public void showTaskList(TaskList taskList) {
        System.out.println("Hey Brotherman, these are the tasks on your list!");
        ArrayList<Task> list = taskList.list();
        int start = 1;
        for (Task listItems : list) {
            System.out.println(start + ". " + listItems.toString());
            start++;
        }
    }
}
