package state;

public class TodoTask extends Task {
    public TodoTask(String title, boolean isDone) {
        super(title, isDone);
    }

    @Override
    public TodoTask mark() {
        return new TodoTask(getTitle(), true);
    }

    @Override
    public TodoTask unmark() {
        return new TodoTask(getTitle(), false);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
