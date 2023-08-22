public class ToDo extends Task {
    public ToDo(int status, String task) {
        super(status, task);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
