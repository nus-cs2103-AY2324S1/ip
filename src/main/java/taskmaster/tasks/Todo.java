package taskmaster.tasks;

public class Todo extends Task {

    public Todo(String description, String marked) {
        super(description, marked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
