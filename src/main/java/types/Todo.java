package types;

public class Todo extends types.Task {
    public Todo(String description) {
        super(description);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
