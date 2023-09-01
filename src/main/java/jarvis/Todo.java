package jarvis;

public class Todo extends Task {
    public Todo(String title, boolean isCompleted) {
        super(title, isCompleted);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
