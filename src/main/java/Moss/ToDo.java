package Moss;

public class ToDo extends Task{
    /**
     * Constructs a Moss.ToDo object with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }

    @Override
    public String toString(String x) {
        return "T | " + super.toString();
    }
}
