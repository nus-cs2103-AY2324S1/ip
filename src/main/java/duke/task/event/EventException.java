package duke.task.event;

import duke.DukeException;

/**
 * The EventException Class is used when user does not follow Event input format.
 */
public class EventException extends DukeException {

    public EventException() {
        super();
    }

    /**
     * This method gives the string representation of EventException
     *
     * @return The String representation of an EventException
     */
    @Override
    public String toString() {
        return "☹ This is not a valid event input";
    }
}
