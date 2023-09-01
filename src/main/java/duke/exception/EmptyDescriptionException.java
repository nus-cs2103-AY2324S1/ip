package duke.exception;

import duke.Ui;

public class EmptyDescriptionException extends Exception {

    /**
     * Constructs an EmptyDescriptionException object by error message provided.
     *
     * @param message The error message.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }

    /**
     * Overrides the `toString' method of the parent class to provide a formatted error message.
     *
     * @return A formatted error message indicating that the details of the command inputted is empty.
     */
    @Override
    public String toString() {

        String result = "\n\tOOPS! The description of the " + getMessage() + " cannot be empty.";
        return Ui.showLine() + result + " \n" + Ui.showLine();
    }
}
