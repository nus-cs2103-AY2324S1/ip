package duke;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getSavingFormat() {
        return "[T] | [" + getStatusIcon() + "] | " + description;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
