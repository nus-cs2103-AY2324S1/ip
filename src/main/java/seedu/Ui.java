package seedu;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

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
    public String showWelcome(VBox dialogContainer) {
        String logo = "Bacon Pancake";
        return (" Hello from " + logo + "\n What can I do for you? \n" + "---------------------------------- \n");
    }

    /**
     * Prints ending message to the user
     */
    public String showByeMessage() {
        return ("Bacon Pancake : \n" + "Bye! See you again soon ");
    }




    /**
     * Prints out all the tasks in the list of tasks
     *
     * @param tasks the TaskList class that contains all the current tasks
     */
    public String showTask(TaskList tasks) {
        StringBuilder s = new StringBuilder();
        s.append("Bacon Pancake : Below are the lists\n");
        int curr = 1;
        for (int i = 0; i < tasks.getLen(); i++) {
            if (tasks.get(i) != null) {
                Task t = tasks.get(i);
                s.append((curr++) + ". " + t.getStatus() + "\n");
            }
        }
        assert curr >= tasks.getLen() : "Something wrong with the task length";
        return s.toString();
    }

    /**
     * Generates and prints the error message upon any exceptions.
     * @param m message of the error
     */
    public String showError(String m) {
        return m;
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
    public String removeTask(Task t, int currentLength) {
        StringBuilder s = new StringBuilder();
        s.append("Noted. I've removed this task: \n");
        s.append(t.toString() + "\n");
        s.append("Now you have " + currentLength + " tasks left. \n");
        return s.toString();
    }

    /**
     * Marks and checks the current task to be true
     */
    public String showMarked() {
        return ("Bacon Pancake : Nice! I've marked this task as done:");
    }

    /**
     * Add a task to the lists of tasks
     *
     * @param curr Task that is supposed to be added to the lists of tasks
     */

    public String addTask(Task curr) {
        return ("Bacon Pancake : \n Added : " + curr.description);
    }

    public String showFoundWords(ArrayList<Task> tasks) {
        StringBuilder s = new StringBuilder();
        for(Task task : tasks) {
            s.append(task.getStatus());
        }
        return s.toString();
    }

}
