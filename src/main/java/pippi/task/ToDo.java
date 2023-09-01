package pippi.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of a ToDo task to the UI
     * @return ToDo string representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the String representation of a ToDo task written to the memory
     * @return ToDo string representation
     */
    @Override
    public String toMemory() { return "T " + super.getStatus() + super.getDescription(); }
}
