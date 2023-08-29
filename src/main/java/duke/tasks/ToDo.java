package duke.tasks;

/*
 * Represents tasks without any date/time attached to it.
 */
public class ToDo extends Task {

    /*
     * Creates a new ToDo object.
     *
     * @param description The description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getSaveFormat() {
        return String.format("TODO||%d||%s\n", this.getIsDone() ? 1 : 0, this.getDescription());
    }

    /*
     * Returns the string representation of the ToDo.
     *
     * @return The string representation of the ToDo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
