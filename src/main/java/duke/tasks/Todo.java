package duke.tasks;

public class Todo extends Task {
    private static final String DONE_FLAG = "[X] ";
    private static final String UNDONE_FLAG = "[ ] ";
    private static final String PRINT_FORMAT = "[T]%s %s";
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        String status = this.isDone() ? DONE_FLAG : UNDONE_FLAG;
        return String.format(PRINT_FORMAT, status, this.getDescription());
    }
}

