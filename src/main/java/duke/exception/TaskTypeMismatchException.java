package duke.exception;

public class TaskTypeMismatchException extends Exception {
    public TaskTypeMismatchException(String message) {
        super(message + ":" + "\n\tAccio error! It appears the Task type of the entered index does not match the " +
                "type of the intended Task to be updated." +
                " Type 'help' to see the list of valid inputs and the expected formats! ");
    }
}
