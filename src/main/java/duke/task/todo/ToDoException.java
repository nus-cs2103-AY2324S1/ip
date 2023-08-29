package duke.task.todo;

import duke.DukeException;

/**
 * The ToDoException Class is used when user does not follow Event input format.
 */
public class ToDoException extends DukeException {
    public ToDoException() {
        super();
    }

    /**
     * This method gives the string representation of ToDoException
     *
     * @return The String representation of an ToDoException
     */
    @Override
    public String toString(){
        return "☹ This is not a valid Todo input";
    }

}
