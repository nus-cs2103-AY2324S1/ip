public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
    
}
