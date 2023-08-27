package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String msg = "[T]" + super.toString();
        return msg;
    }
}
