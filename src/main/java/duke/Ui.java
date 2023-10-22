package duke;

import duke.message.ErrorMessage;
import duke.message.InvalidCommandMessage;
import duke.message.WelcomeMessage;
import duke.templates.MessageTemplates;

/**
 * Represents the Ui.
 */
public class Ui {
    /**
     * Returns line.
     */
    public String getLine() {
        return MessageTemplates.MESSAGE_LINE;
    }
    /**
     * Returns welcome message.
     */
    public String getWelcomeMessage() {
        return new WelcomeMessage().send();
    }
    /**
     * Returns the menu.
     */
    public String getInvalidCommandMessage() {
        return new InvalidCommandMessage().send();
    }
    /**
     * Returns an invalid index error.
     */
    public String getInvalidIndexError() {
        return new ErrorMessage(MessageTemplates.MESSAGE_INVALID_INDEX).send();
    }
    /**
     * Returns a save data error.
     */
    public String getSaveDataError() {
        return new ErrorMessage(MessageTemplates.MESSAGE_SAVE_DATA_ERROR).send();
    }
    /**
     * Returns an error message.
     * @param message Error message.
     */
    public String getError(String message) {
        return new ErrorMessage(message).send();
    }
}
