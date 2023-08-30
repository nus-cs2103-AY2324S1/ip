package duke;

public class ToDo extends Task {
    protected String taskDescription;
    private String identifier;

    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
        this.identifier = "[T]";
    }

    public String toString() {
        return this.identifier + super.toString();
    }
}
