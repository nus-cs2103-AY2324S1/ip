package seedu;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private boolean on;
    private final PrintStream out;
    public Ui() {
        this.sc = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Prints welcome message to the user
     */
    public void showWelcome() {
        String logo = "Bacon Pancake";
        out.println(" Hello from " + logo + "\n What can I do for you? \n" + "---------------------------------- \n");
    }

    /**
     * Prints ending message to the user
     */
    public boolean showByeMessage() {
        out.println("Bacon Pancake : \n" + "Bye! See you again soon ");
        return false;
    }


    /**
     * Prints out all the tasks in the list of tasks
     *
     * @param tasks the TaskList class that contains all the current tasks
     */
    public void showTask(TaskList tasks) {
        out.println("Bacon Pancake : Below are the lists\n");
        int curr = 1;
        for (int i = 0; i < tasks.getLen(); i++) {
            if (tasks.get(i) != null) {
                Task t = tasks.get(i);
                out.println((curr++) + ". " + t.getStatus());
            }
        }
    }

    /**
     * Generates and prints the error message upon any exceptions.
     * @param m message of the error
     */
    public void showError(String m) {
        out.println(m);
    }

    /**
     * Generates and prints a horizontal line.
     */
    public void showLine() {
        out.println("----------------------------------");
    }

    /**
     * Returns the command that is entered by the user
     *
     * @return string of comman (full line) entered by the user
     */

    public String getUserCommand() {
        out.println("Enter your input : ");
        return sc.nextLine();
    }

    /**
     * Removes a task from the lists of tasks
     *
     * @param t Task that is to be removed
     * @param currentLength to show the current number of tasks after removal
     */
    public void removeTask(Task t, int currentLength) {
        out.println("Noted. I've removed this task: \n");
        out.println(t);
        out.println("Now you have " + currentLength + " tasks left.");
    }

    /**
     * Marks and checks the current task to be true
     */
    public void showMarked() {
        out.println("Bacon Pancake : Nice! I've marked this task as done:");
    }

    /**
     * Add a task to the lists of tasks
     *
     * @param curr Task that is supposed to be added to the lists of tasks
     */

    public void addTask(Task curr) {
        out.println("Bacon Pancake : \n Added : " + curr.description);
    }

}
