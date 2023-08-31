package helpbuddy.task;

import helpbuddy.exception.HelpBuddyException;

/**
 * This class is a subclass of Task which defines a todo task.
 */
public class ToDo extends Task {
    /**
     * A constructor for todo task object.
     * @param description Name of todo task
     * @throws HelpBuddyException If the description is empty
     */
    public ToDo(String description) throws HelpBuddyException {
        super(description);
        if (description.isBlank()) {
            throw new HelpBuddyException("The description of a todo cannot be empty.\n");
        }
    }

    /**
     * @return String representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String stringifyTask() {
        return String.format("T|%d|%s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof ToDo) {
            ToDo task = (ToDo) obj;
            return this.stringifyTask().equals(task.stringifyTask());
        }

        return false;
    }
}
