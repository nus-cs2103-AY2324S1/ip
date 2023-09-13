package zean.task;

import zean.exception.ZeanException;

/**
 * The class that represents a todo task.
 *
 * @author Zhong Han
 */
public class Todo extends Task {

    /**
     * Constructor for the todo task.
     *
     * @param bool The completion status of the todo task.
     * @param description The description of the todo task.
     */
    public Todo(String bool, String description) {
        super(bool, description.strip());
        assert description != null;
        if (Integer.parseInt(bool) == 1) {
            this.isDone = true;
        }
    }

    public void updateDates(String by, String from, String to) {
        if (!by.isBlank() || !from.isBlank() || !to.isBlank()) {
            throw new ZeanException("Cannot update by/from/to dates for todo task!");
        }
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return S string comprising the symbol, status
     *      and the description of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representing the task to be written in the disk.
     *
     * @return The string describing the task to be written in the disk.
     */
    @Override
    public String toStringForFile() {
        return "T | " + super.toStringForFile();
    }
}
