package duke.task;

/**
 * Represent a to-do task.
 * A to-do task does not have a start or end time.
 */
public class ToDo extends Task {
    /**
     * Instantiates the to-do task with the given content.
     * @param name the content of the task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Data representation of this to-do task, to be stored in disk.
     * @return the string representation of this task in disk
     */
    @Override
    public String data() {
        return "T " + super.data();
    }

    /**
     * String representation of this to-do task to be printed in UI.
     * @return the string representation of this task to be printed in UI
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks whether this to-do task is the same as another task, for testing purposes.
     * @param another the object to compare with
     * @return whether this to-do task is the same as the given other task
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof ToDo) {
            return super.equals(another);
        }
        return false;
    }
}
