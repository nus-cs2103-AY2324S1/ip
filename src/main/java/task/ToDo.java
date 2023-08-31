package task;
public class ToDo extends Task {

    protected String type = "task.ToDo";
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return "[T]";
    }

}
