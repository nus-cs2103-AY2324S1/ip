public class Todo extends Task{
    private String type = "T";

    public Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
