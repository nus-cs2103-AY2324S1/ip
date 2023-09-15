package duke.lib;

import duke.error.DukeException;


/**
 * Represents the user interface of the Duke application.
 */
public class UI {
    private String currentStatus = "";

    private void appendStatus(String status) {
        currentStatus += status + "\n";
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        String logo =
                "  ______  __    __       ___       _______  \n"
                        + " /      ||  |  |  |     /   \\     |       \\ \n"
                        + "|  ,----'|  |__|  |    /  ^  \\    |  .--.  |\n"
                        + "|  |     |   __   |   /  /_\\  \\   |  |  |  |\n"
                        + "|  `----.|  |  |  |  /  _____  \\  |  '--'  |\n"
                        + " \\______||__|  |__| /__/     \\__\\ |_______/\n";


        showLine();
        appendStatus("Hi!! I am");
        appendStatus(logo);
        appendStatus("What brings you here today?");
        showLine();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        appendStatus("Oh.. bye");
    }

    /**
     * Displays an error message for loading data from the save file.
     */
    public void showLoadingError() {
        appendStatus("Error reading from save file.");
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        appendStatus("");
    }

    /**
     * Displays an error message.
     *
     * @param error The DukeException containing the error message.
     */
    public void showError(DukeException error) {
        appendStatus(error.getMessage());
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        appendStatus(message);
    }

    public String getStatus() {
        String tmp = currentStatus;
        this.currentStatus = "";
        return tmp;
    }
}
