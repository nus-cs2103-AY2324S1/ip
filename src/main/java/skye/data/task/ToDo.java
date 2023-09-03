package skye.data.task;

/**
 * Represents a ToDo task that the user wishes to keep track of.
 */
public class ToDo extends Task{

    /**
     * Initialises an ToDo object
     *
     * @param description A short description of the ToDo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Encodes the ToDo object into a formatted string to be saved into a text file.
     *
     * @return Encoded string of ToDo object
     */
    @Override
    public String toSaveDataFormat() {
        return String.format("T | %d | %s", isDone() ? 1 : 0, getDescription());
    }

    /**
     * Produces a string representation of a ToDo object.
     *
     * @return String representation of ToDo object
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Checks if another ToDo task is equivalent to the current ToDo task.
     *
     * @param other The other ToDo object we are checking for equivalence
     * @return if the other ToDo object is equivalent to the current object
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ToDo) {
            ToDo otherToDo = (ToDo) other;
            return (this.description.equals(otherToDo.description));
        } else {
            return false;
        }
    }
}