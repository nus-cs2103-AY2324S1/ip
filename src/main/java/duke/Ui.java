package duke;

import duke.message.ErrorMessage;
import duke.message.MenuMessage;
import duke.message.WelcomeMessage;
import duke.templates.MessageTemplates;

/**
 * Represents the Ui.
 */
public class Ui {
    /**
     * Constructor for Ui.
     */
    public Ui() {
        new WelcomeMessage().send();
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println(MessageTemplates.MESSAGE_LINE);
    }

    /**
     * Prints the menu.
     */
    public void showMenu() {
        new MenuMessage().send();
    }

    /**
     * Prints an invalid index error.
     */
    public void showInvalidIndexError() {
        new ErrorMessage(MessageTemplates.MESSAGE_INVALID_INDEX).send();
    }

    /**
     * Prints a save data error.
     */
    public void showSaveDataError() {
        new ErrorMessage(MessageTemplates.MESSAGE_SAVE_DATA_ERROR).send();
    }

    /**
     * Prints an error message.
     * @param message Error message.
     */
    public void showError(String message) {
        new ErrorMessage(message).send();
    }
}
