package duke.exception;

import duke.Ui;

public class EmptyDescriptionException extends Exception {

    public EmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {

        String result = "\n\tOOPS! The description of the " + getMessage() + " cannot be empty.";
        return Ui.showLine() + result + " \n" + Ui.showLine();
    }
}
