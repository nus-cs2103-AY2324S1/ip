package tasks;

/**
 * An exception class representing an index out of bounds error specific to the TaskList class.
 * This exception is thrown when attempting to access or manipulate tasks using an invalid index.
 */
public class TaskListIndexOutOfBoundsException extends ArrayIndexOutOfBoundsException{
    /**
     * Constructs a TaskListIndexOutOfBoundsException with the specified detail message.
     *
     * @param msg The detail message indicating the cause of the exception.
     */
    public TaskListIndexOutOfBoundsException(String msg) {
        super(msg);
    }

    /**
     * Constructs a TaskListIndexOutOfBoundsException with the specified invalid index.
     *
     * @param index The index that caused the exception.
     */
    public TaskListIndexOutOfBoundsException(int index) {
        super(index);
    }
}
