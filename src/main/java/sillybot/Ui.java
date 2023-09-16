package sillybot;

import sillybot.exceptions.WrongMarkException;
import sillybot.tasks.Task;

/**
 * Represents an Ui object that deals with user input and output.
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
        String logo = "  /\\______/\\ \n"
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
        return "See ya! I know you are gonna procrastinate again.";
    }

    /**
     * Prints message if task is already done.
     *
     * @throws WrongMarkException If the task is already done.
     */
    public String showAlreadyDone() throws WrongMarkException {
        throw new WrongMarkException("So silly ah! How mark already done work? You trying to be smart?");
    }

    /**
     * Prints message if index is invalid.
     *
     * @return The message for invalid index.
     */
    public String showInvalidIndex() {
        return "Aiyo! That does not even exist!";
    }

    /**
     * Prints message if task is already undone.
     */
    public String showAlreadyUndone() throws WrongMarkException {
        throw new WrongMarkException("Yea I know you didn't do it! Why tell me again? Start working now!");
    }

    /**
     * Prints message for deleting task.
     *
     * @param task The task to be deleted.
     * @return The message for deleting task.
     */
    public String deleteTaskMessage(Task task) {
        String output = "HAhahah! You couldn't do it. Could ya?\n"
                + task
                + "\n"
                + "Now you have "
                + Task.getTaskCount()
                + " tasks in the list.";

        return output;
    }

    /**
     * Prints message for adding task.
     *
     * @param task The task to be added.
     * @return The message for adding task.
     */
    public String addTaskMessage(Task task) {
        String output = "For some reason I believe you are gonna do that!\n"
                + task
                + "\n"
                + "Now you have "
                + Task.getTaskCount()
                + " tasks in the list.";

        return output;
    }

    /**
     * Prints error message for saving task.
     *
     * @return The error message.
     */
    public String showSaveError() {
        return "Something went wrong while saving your tasks. Remember I am silly! :(.";
    }

    /**
     * Prints error message for loading task.
     */
    public void showLoadingError() {
        System.out.println("Something went wrong while loading your tasks. :(.");
    }
}
