package exceptions;

import exceptions.BarbieException;

public class BarbieTaskNumberException extends BarbieException {
    public BarbieTaskNumberException() {
        super("Task to mark or unmark or del is not provided as a digit!\n"
                + "\n" + "use the list command to see the digit of your task and make sure to give \n"
                + "the digit of the task you want to mark/unmark/del. (e.g. mark 2)");
    }
}
