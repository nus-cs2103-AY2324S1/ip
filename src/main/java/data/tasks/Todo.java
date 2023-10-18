package data.tasks;

public class Todo extends Task {
    public Todo(String detail) {
        super(detail);
    }

    @Override
    public String toFileFormatString() {
        return String.format(
            "T|%s",
            super.toFileFormatString()
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[T]%s", super.toString()
        );
    }
}
