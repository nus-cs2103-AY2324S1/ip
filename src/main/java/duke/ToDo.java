package duke;

public class ToDo extends Task {

    public static ToDo create(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("Err: Empty Description");
        }
        return new ToDo(description);
    }
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String fileString() {
        return String.format(
                "todo %d %s",
                super.isDone ? 1 : 0,
                super.description
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[T][%s] %s",
                super.getStatusIcon(),
                super.description
        );
    }
}
