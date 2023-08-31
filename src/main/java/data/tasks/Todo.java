package data.tasks;

public class Todo extends Task {
    public Todo(String detail) {
        super(detail);
    }

    public Todo(String detail, boolean isDone) {
        super(detail, isDone);
    }

    @Override
    public String toString() {
        return String.format(
            "[T]%s", super.toString()
        );
    }

    @Override
    public String toFileFormatString() {
        return String.format(
            "T|%s",
            super.toFileFormatString()
        );
    }
}
