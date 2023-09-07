package duke;

import java.util.Scanner;

import duke.exceptions.WrongMarkException;
import duke.tasks.Task;

/**
 * Represents a Ui object that deals with user input and output.
 */
public class Ui {
    /**
     * Creates a Ui object.
     */
    public Ui() {
    }

    /**
     * Prints the welcome message.
     *
     * @return The welcome message.
     */
    public String showWelcome() {
        separatorLines();
        String logo = ""
                + "  /\\______/\\ \n"
                + " /  O    O  \\ \n"
                + "|    -_-     | \n"
                + "|   \\___/    | \n"
                + " \\__________/ \n";
        String welcomeMessage = "Call me sillyBOT\n"
                + "Tell me what silly things you are gonna do, if you are haha?";
        System.out.println(logo + welcomeMessage);
        return logo + welcomeMessage;
    }

    /**
     * Prints the exit message.
     *
     * @return The exit message.
     */
    public String showExit() {
        separatorLines();
        System.out.println("See ya! I know you are gonna procrastinate again.");
        separatorLines();
        return "See ya! I know you are gonna procrastinate again.";
    }

    /**
     * Prints the separator lines.
     */
    public void separatorLines() {
        System.out.println("_".repeat(50));
    }

    /**
     * Gets the user input.
     *
     * @return The user input.
     */
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        separatorLines();
        System.out.println("What do you want to do, eh?");
        return sc.nextLine();
    }

    /**
     * Prints message if task is already done.
     *
     * @throws WrongMarkException If the task is already done.
     */
    public String showAlreadyDone() throws WrongMarkException {
        separatorLines();
        throw new WrongMarkException("So silly ah! How mark already done work? You trying to be smart?");
    }

    /**
     * Prints message if index is invalid.
     *
     * @return The message for invalid index.
     */
    public String showInvalidIndex() {
        separatorLines();
        System.out.println("Aiyo! That does not even exist!");
        return "Aiyo! That does not even exist!";
    }

    /**
     * Prints message if task is already undone.
     */
    public String showAlreadyUndone() throws WrongMarkException{
        separatorLines();
        throw new WrongMarkException("Yea I know you didn't do it! Why tell me again? Start working now!");
    }

    /**
     * Prints message for deleting task.
     *
     * @param task The task to be deleted.
     *
     * @return The message for deleting task.
     */
    public String deleteTaskMessage(Task task) {
        separatorLines();
        String output = "HAhahah! You couldn't do it. Could ya?\n" + task + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.";
        System.out.println(output);
        return output;
    }

    /**
     * Prints message for adding task.
     *
     * @param task The task to be added.
     *
     * @return The message for adding task.
     */
    public String addTaskMessage(Task task) {
        separatorLines();
        String output = "For some reason I believe you are gonna do that!\n" + task + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.";
        System.out.println(output);
        return output;
    }

    /**
     * Prints error message for saving task.
     *
     * @return The error message.
     */
    public String showSaveError() {
        separatorLines();
        System.out.println("Something went wrong while saving your tasks. Remember I am silly! :(.");
        return "Something went wrong while saving your tasks. Remember I am silly! :(.";
    }

    /**
     * Prints error message for loading task.
     *
     * @return The error message.
     */
    public String showLoadingError() {
        separatorLines();
        System.out.println("Something went wrong while loading your tasks. :(.");
        return "Something went wrong while loading your tasks. :(.";
    }
}
