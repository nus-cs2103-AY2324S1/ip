package exceptions;
/**
 * Custom exception class to handle cases where a collection of tasks is found to be empty
 * or when index is not found inside the task elements.
 */
public class EmptyTasksException extends Exception {

    /**
     * Public constructor for throwing EmptyTasksException
     * @param message string of text
     */
    public EmptyTasksException(String message) {
        super("Macho! The number you entered is incorrect! The task list is empty"
                + " or not part of the tasks, macho!\n" + message);
    }

}
