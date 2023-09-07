public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }
    @Override
    public String getStatus() {
        return "[To-Do]" + super.getStatus();
    }
}