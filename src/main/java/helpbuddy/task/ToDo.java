package helpbuddy.task;

import helpbuddy.exception.HelpBuddyException;

/**
 * The ToDo class creates a Task storing the description of ToDo.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo object with specified String description.
     * @param description the description of Task.
     * @throws HelpBuddyException if the description has no value.
     */
    public ToDo(String description) throws HelpBuddyException {
        super(description);
        if (description.isBlank()) {
            throw new HelpBuddyException("The description of a todo cannot be empty.\n");
        }
    }

    /**
     * Returns String representing the ToDo object.
     * @return a string representation of description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns String representing the ToDo object to be saved in a file.
     * @return a string representation of description.
     */
    @Override
    public String stringifyTask() {
        return String.format("T|%d|%s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Compares the object to the specified object. The result is true if and only if argument is not null and
     * its stringifyTask() is the same as the object's.
     * @param obj the object to compare with.
     * @return true if objects are the same; false otherwise.
     */
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
