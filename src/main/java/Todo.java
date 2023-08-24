public class Todo extends Task {
    private static final String type = "[T]";
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return type + super.toString();
    }
}
