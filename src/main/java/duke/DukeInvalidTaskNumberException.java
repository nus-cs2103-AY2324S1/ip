package duke;

/**
 * Represents a Duke exception when an invalid task number is entered.
 */
public class DukeInvalidTaskNumberException extends DukeException {

    private String taskNum;

    /**
     * Constructor for the exception.
     *
     * @param taskNum Invalid task number entered.
     */
    public DukeInvalidTaskNumberException(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("Error: There is no task with task number %s!", this.taskNum);
    }
}
