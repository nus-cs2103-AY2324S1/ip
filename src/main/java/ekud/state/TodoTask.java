package ekud.state;

public class TodoTask extends Task {
    public TodoTask(String title, boolean isDone) {
        super(title, isDone);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
