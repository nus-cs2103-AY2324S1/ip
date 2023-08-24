public class ToDo extends Task {
    public ToDo (String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskType() {
        return "[T]";
    }

    @Override
    public String getTaskTime() {
        return "";
    }

}
