public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getDescription() {
        return "T" + super.getDescription();
    }
}
