package nobita.ui;

import nobita.task.Task;
import nobita.task.TaskList;

import java.util.Scanner;

/**
 *  Class that encapsulates Ui.
 *  Ui is used to display message to the user.
 *
 *  @author Zheng Chenglong
 */
public class Ui {

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Nobita");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads user input.
     *
     * @return A String that represents the user input.
     */
    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Print the exit message.
     */
    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("________________________________________________________");
    }

    /**
     * Prints a error message.
     *
     * @param message The error message to be display to user.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message.
     *
     * @param message The message to be display to user.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showList(TaskList list) {
        if (list.getTotalTask() < 1) {
            System.out.println("There are no tasks to be shown.");
        }
        else {
            int listNum = 1;
            for (Task task: list) {
                System.out.println(listNum + ". " + task);
                ++listNum;
            }
        }
    }
}
