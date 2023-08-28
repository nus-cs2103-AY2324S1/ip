public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String getDetails() {
        return super.getDetails();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
