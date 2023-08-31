public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    public String toStorageString() {
        return "T // " + this.getStatusIcon() + " // " + this.getDescription();
    }
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
    
}
