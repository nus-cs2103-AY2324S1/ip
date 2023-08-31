public class ToDo extends Task {

    protected String type = "ToDo";
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return "[T]";
    }

}
