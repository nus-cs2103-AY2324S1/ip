/**
 * ToDos are tasks that have no date/time attached to them.
 */
public class ToDo extends Task {
    static final String SYMBOL = "T";

    ToDo(String description) {
        super(description);
    }

    ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + super.getDescription();
    }

    @Override
    public String convertToStorageForm() {
        final String separator = "::";
        final String status = isDone() ? "1" : "0";

        return SYMBOL + separator + status + separator + getDescription();
    }
}
