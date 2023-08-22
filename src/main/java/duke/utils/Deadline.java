package duke.utils;

public class Deadline extends Task {
    private final String end;
    public Deadline (String description) {
        super();

        String[] tokens = description.split(" /by ");
        this.description = tokens[0];
        this.end = tokens[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.end + ")";
    }
}
