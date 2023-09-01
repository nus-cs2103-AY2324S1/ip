package duke.task;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String contentLine() {
        return "T" + super.contentLine();
    }

    @Override
    public String toString() {

        String result = "[T]" + super.toString();
        return result;
    }
}
