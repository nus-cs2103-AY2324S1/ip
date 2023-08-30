package chatbot;

public class Todo extends Task {
    
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String getExtras() {
        return "";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() + super.description;
    }
}