package duke.task.deadline;

import duke.DukeException;

/**
 * The DeadlineException Class is used when user does not follow the deadline input format.
 */
public class DeadlineException extends DukeException {
    public DeadlineException() {
        super();
    }

    /**
     * This method gives the string representation of DeadlineException
     *
     * @return The String representation of an DeadlineException
     */
    @Override
    public String toString(){
        return "☹ This is not a valid Deadline input";
    }
}
