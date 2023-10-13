package duke.exception;

import duke.Ui;

public class NoSuchCommandException extends Exception {

    /**
     * Overrides the `toString' method of the parent class to provide a formatted error message.
     *
     * @return A formatted error message indicating the bot does not understand the command.
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry , but I don't know what that means :-( ";
    }
}
