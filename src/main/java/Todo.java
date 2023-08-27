public class Todo extends Task {
    public Todo(String description, boolean marked) {
        super(description, "todo");
        this.mark(marked, true);
    }

    @Override
    public String getOriginalMessage() {
        return "todo " + this.getDescription();
    }
}

