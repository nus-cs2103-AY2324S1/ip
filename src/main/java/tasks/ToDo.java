package tasks;

public class ToDo extends Task {

    private String TaskIcon = "[T]";

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        // Constructor for functions.Load
        super(description, isDone);
    }

    @Override
    public String getTaskAsString() {
        String message = String.format("%s[%s] %s", this.TaskIcon,this.getStatusIcon(), this.getDescription());
        return message;
    }
}
