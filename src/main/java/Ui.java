import java.util.Scanner;

/**
 * Represents a UI class to handle user interactions.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Ui {
    private static final String DIVIDER = "-----------------------";
    /** Scanner object that takes in user inputs. */
    private Scanner scanner;

    /**
     * Prints a welcome message when user starts the chatbot.
     */
    public void showWelcome() {
        System.out.println(DIVIDER + "\nHello! I'm Chatter!" + "\nHow can i help you today?" + DIVIDER);
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
        this.scanner.close();
        System.out.println(DIVIDER + "\nBye. Hope to see you again soon!");
    }

    /**
     * Prints a welcome message when user exits the chatbot.
     */
    public void showLoadingError() {
        System.out.println(DIVIDER + "\nError reading data from file!" + DIVIDER);
    }

    /**
     * Reads the user input and returns a string of the raw user input.
     *
     * @return A string of the raw user input.
     */
    public String readCommand() {
        this.scanner = new Scanner(System.in);
        return this.scanner.nextLine();
    }

    /**
     * Prints task string when task is added to the list.
     *
     * @param task Task object being added to the list.
     */
    public void showAddedTask(Task task, int numOfTasks) {
        System.out.println("Got it. I have added this task to do:");
        System.out.println("  " + task.toString());
        System.out.println("You now have " + numOfTasks + " task(s) in the list.");
    }
}
