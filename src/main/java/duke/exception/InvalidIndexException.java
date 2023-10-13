package duke.exception;

import duke.Ui;

public class InvalidIndexException extends Exception {

    /**
     * Overrides the `toString' method of the parent class to provide a formatted error message.
     *
     * @return A formatted error message informing the user to provide a proper index.
     */
    @Override
    public String toString() {
        return "OOPS! Please choose a proper index.";
    }
}
