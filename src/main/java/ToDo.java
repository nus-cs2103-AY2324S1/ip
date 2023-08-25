/**
 * A ToDo is the most basic type of Task. It only needs to be supplied with its description.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
