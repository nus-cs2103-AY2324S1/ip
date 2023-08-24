public class ToDo extends Task {
    ToDo(String task) {
        super(task);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }
}
