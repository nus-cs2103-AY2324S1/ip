public class Todo extends Task{
    private String type = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
