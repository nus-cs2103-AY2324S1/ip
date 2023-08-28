public class ToDo extends Task {
    protected String type = "T";
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getTaskType() {
        return "[" + this.type + "]";
    }
}
