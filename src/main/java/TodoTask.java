public class TodoTask extends Task {
    public TodoTask(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
