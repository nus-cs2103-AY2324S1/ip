package crackerpackage.tasks;

import exceptions.EmptyDescriptionException;


/**
 * A normal task.
 *
 * @author Anton Tan Hong Zhi
 */
public class Todo extends Task {
    /**
     * Creates a Todo object
     *
     * @param s description of the Task
     * @throws EmptyDescriptionException
     */

    public Todo(String s) throws EmptyDescriptionException {
        super(s);
    }


    /**
     * Returns the String representation of the Todo object.
     *
     * @return a string representing the Todo object.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
