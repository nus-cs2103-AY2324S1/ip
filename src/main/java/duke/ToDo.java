package duke;

public class ToDo extends Task {


    public ToDo(String details) {
        super(details);
    }

    public ToDo(String details, boolean isCompleted) {
        super(details, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
