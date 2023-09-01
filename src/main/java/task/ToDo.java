package task;

/**
 * This class encapsulates a ToDo.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }
    public ToDo(int status, String description) {
        super(description, status != 0);     //if 0, return false, else return true
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String storeToDiskFormat() {
        return "T" + "|" + this.getStatus() + "|" + this.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
