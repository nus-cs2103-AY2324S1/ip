package arona.task;

public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask(String description, int isMarked) {
        super(description, isMarked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
