package duke.task;

/**
 * Represent a to-do task.
 * A to-do task does not have a start or end time.
 */
public class ToDo extends Task {
    /**
     * Instantiates the to-do task with the given content.
     * @param name The content of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Data representation of this to-do task, to be stored in disk.
     * @return The string representation of this task in disk.
     */
    @Override
    public String getData() {
        return "T " + super.getData();
    }

    /**
     * String representation of this to-do task to be printed in UI.
     * @return The string representation of this task to be printed in UI.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks whether this to-do task is the same as another task, for testing purposes.
     * @param another The object to compare with.
     * @return Whether this to-do task is the same as the given other task.
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof ToDo) {
            return super.equals(another);
        }
        return false;
    }
}
