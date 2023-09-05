package duke.tasks;

public class Todo extends Task {
    private static final String PRINT_FORMAT = "[T]%s %s";
    private static final String STORE_FORMAT = "[T] | %s | %s";
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String saveString() {
        return String.format(STORE_FORMAT, getFlag(), this.getDescription().trim());
    }
    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, getFlag(), this.getDescription());
    }
}

