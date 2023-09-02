public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        // Convert task to file format string
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
    
}
