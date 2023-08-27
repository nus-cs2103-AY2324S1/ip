package duke;

import duke.message.ErrorMessage;
import duke.message.MenuMessage;
import duke.message.WelcomeMessage;
import duke.templates.MessageTemplates;

public class Ui {
    public Ui() {
        new WelcomeMessage().send();
    }

    public void showLine() {
        System.out.println(MessageTemplates.MESSAGE_LINE);
    }

    public void showMenu() {
        new MenuMessage().send();
    }

    public void showInvalidIndexError() {
        new ErrorMessage(MessageTemplates.MESSAGE_INVALID_INDEX).send();
    }

    public void showSaveDataError() {
        new ErrorMessage(MessageTemplates.MESSAGE_SAVE_DATA_ERROR).send();
    }

    public void showError(String message) {
        new ErrorMessage(message).send();
    }
}
