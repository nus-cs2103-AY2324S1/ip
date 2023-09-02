package duke;

import duke.exceptions.WrongMarkException;
import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    private final String LOGO = "" +
            "  /\\______/\\ \n" +
            " /  O    O  \\ \n" +
            "|    -_-     | \n" +
            "|   \\___/    | \n" +
            " \\__________/ \n";

    /**
     * Creates a Ui object.
     */
    public Ui() {
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        separatorLines();
        System.out.println(LOGO);
        System.out.println("Call me sillyBOT\n" +
                "Tell me what silly things you are gonna do, if you are haha?");
    }

    /**
     * Prints the exit message.
     */
    public void showExit() {
        separatorLines();
        System.out.println("See ya! I know you are gonna procrastinate again.");
        separatorLines();
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
    public void showAlreadyDone() throws WrongMarkException {
        separatorLines();
        throw new WrongMarkException("So silly ah! How mark already done work? You trying to be smart?");
    }

    /**
     * Prints message if index is invalid.
     */
    public void showInvalidIndex() {
        separatorLines();
        System.out.println("You silly ah! That does not even exist! Wake up!!");
    }

    /**
     * Prints message if task is already undone.
     *
     * @throws WrongMarkException If the task is already undone.
     */
    public void showAlreadyUndone() throws WrongMarkException {
        separatorLines();
        throw new WrongMarkException("Yea I know you didn't do it! Why tell me again? Start working now!");
    }

    /**
     * Prints message for deleting task.
     */
    public void deleteTaskMessage(Task task) {
        separatorLines();
        System.out.println("HAhahah! You couldn't do it. Could ya?\n" + task + "\n" +
                "Now you have " + Task.taskCount + " tasks in the list.");
    }

    /**
     * Prints message for adding task.
     *
     * @param task The task to be added.
     */
    public void addTaskMessage(Task task) {
        separatorLines();
        System.out.println("For some reason I believe you are gonna do that!\n" + task + "\n" +
                "Now you have " + Task.taskCount + " tasks in the list.");
    }

    /**
     * Prints error message for saving task.
     */
    public void showSaveError() {
        separatorLines();
        System.out.println("Something went wrong while saving your tasks. Remember I am silly! :(.");
    }

    /**
     * Prints error message for loading task.
     */
    public void showLoadingError() {
        separatorLines();
        System.out.println("Something went wrong while loading your tasks. :(.");
    }
}
