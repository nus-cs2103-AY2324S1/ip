import java.util.StringJoiner;

public class ToDo extends Task {
    protected String by;

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        StringJoiner joiner = new StringJoiner(";");
        joiner.add("T").add(super.toFileFormat());
        return joiner.toString();
    }
}
