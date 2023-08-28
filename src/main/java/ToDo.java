public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String getDescription() {
        return super.toString();
    }

    @Override
    public String statusAndTask() {
        return "[T]" + statusString() + " " + super.toString();
    }
}
