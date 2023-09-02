package arona.task;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, int isMarked) {
        super(description, isMarked);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
