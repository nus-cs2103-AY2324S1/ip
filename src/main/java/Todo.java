public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String getDescription() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
