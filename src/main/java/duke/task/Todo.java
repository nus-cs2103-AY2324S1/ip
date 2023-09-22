package duke.task;

/**
 * A Class that handle a todo task.
 *
 * @author marioalvaro
 */
public class Todo extends Task {
    /**
     * the type Icon
     */
    private static final String TYPE = "T";

    /**
     * constructor for duke.task.Event duke.task
     * @param description the text stored
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * override the toString method
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString();
    }

    /**
     * Method to create string that in match with the storing format.
     * @return string that in match with the storing format.
     */
    @Override
    public String toDataString() {
        return TYPE + " / " + super.toDataString();
    }

    /**
     * Equals method to help checking duplicates.
     *
     * @param obj obj compared with.
     * @return if the object is equal or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Todo) {
            Todo todo = (Todo) obj;

            return super.equals(todo);
        }
        return false;
    }

    /**
     * Check if the text is equal.
     *
     * @param obj Object to compare with.
     * @return if the text is equal or not.
     */
    @Override
    public boolean equalsText(Object obj) {
        return this.equals(obj);
    }
}
