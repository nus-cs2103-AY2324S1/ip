package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        return "[T]" + status + " " + super.description;
    }
}
