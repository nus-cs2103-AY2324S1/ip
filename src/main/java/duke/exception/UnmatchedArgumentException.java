package duke.exception;

import duke.Ui;

public class UnmatchedArgumentException extends Exception {

    /*
        The amount of argument inputted by the user.
     */
    private int got;
    /*
        The actual amount of argument required by the command.
     */
    private int actual;

    /**
     * Construct an UnmatchedArgumentException object with provided number of arguments.
     *
     * @param got The amount of argument inputted by the user.
     * @param actual The actual amount of argument required by the command.
     */
    public UnmatchedArgumentException(int got, int actual) {

        this.got = got;
        this.actual = actual;
    }

    /**
     * Overrides the `toString' method of the parent class to provide a formatted error message.
     *
     * @return A formatted error message indicating that the amount of argument passed in is insufficient.
     */
    @Override
    public String toString() {

        String result = "\n\tOOPS! The argument passed does not match the requirement.";
        result += "\n\tExpected " + this.actual + " , got " + this.got + "\n";
        return Ui.showLine() + result + " \n" + Ui.showLine();
    }
}
