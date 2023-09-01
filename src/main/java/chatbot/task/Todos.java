package chatbot.task;

public class Todos extends Task {
    public Todos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        String done = isDone ? "1" : "0";
        return String.format("T | %s | %s", done, description);
    }
}
