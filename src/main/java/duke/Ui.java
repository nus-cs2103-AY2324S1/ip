package duke;

import duke.exceptions.WrongMarkException;
import duke.tasks.Task;

import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class Ui {
    private final String LOGO = "" +
            "  /\\______/\\ \n" +
            " /  O    O  \\ \n" +
            "|    -_-     | \n" +
            "|   \\___/    | \n" +
            " \\__________/ \n";

    /**
     * Constructor for Ui.
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
     * @return User input.
     */
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        separatorLines();
        System.out.println("What do you want to do, eh?");
        return sc.nextLine();
    }

    /**
     * Display error message when the task is already done.
     */
    public void showAlreadyDone() throws WrongMarkException {
        separatorLines();
        throw new WrongMarkException("So silly ah! How mark already done work? You trying to be smart?");
    }

    /**
     * Display error message when the index is invalid.
     */
    public void showInvalidIndex() {
        separatorLines();
        System.out.println("You silly ah! That does not even exist! Wake up!!");
    }

    /**
     * Display error message when the task is already undone.
     */
    public void showAlreadyUndone() throws WrongMarkException {
        separatorLines();
        throw new WrongMarkException("Yea I know you didn't do it! Why tell me again? Start working now!");
    }

    /**
     * Display delete task message.
     */
    public void deleteTaskMessage(Task task) {
        separatorLines();
        System.out.println("HAhahah! You couldn't do it. Could ya?\n" + task + "\n" +
                "Now you have " + Task.taskCount + " tasks in the list.");
    }

    /**
     * Display add task message.
     *
     * @param task Task added.
     */
    public void addTaskMessage(Task task) {
        separatorLines();
        System.out.println("For some reason I believe you are gonna do that!\n" + task + "\n" +
                "Now you have " + Task.taskCount + " tasks in the list.");
    }

    /**
     * Display save error message.
     */
    public void showSaveError() {
        separatorLines();
        System.out.println("Something went wrong while saving your tasks. Remember I am silly! :(.");
    }

    /**
     * Display load error message.
     */
    public void showLoadingError() {
        separatorLines();
        System.out.println("Something went wrong while loading your tasks. :(.");
    }
}
