public class Todo extends Task {
    public Todo(String description) {
        super(description, "todo");
    }

    public Todo(String description, boolean marked) {
        super(description, "todo");
        this.mark(marked);
    }

    @Override
    public String getOriginalMessage() {
        return "todo " + this.getDescription();
    }
}

