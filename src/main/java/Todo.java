public class Todo extends Task {
    public Todo(String description, boolean marked) {
        super(description, "todo", marked);
    }

    @Override
    public String getOriginalMessage() {
        return "todo " + this.getDescription();
    }
}

