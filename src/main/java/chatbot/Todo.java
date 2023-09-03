package chatbot;

/**
  * Stores the task details for todo.
  */
public class Todo extends Task {
    
    /**
     * Constructor for Todo. Initialises the description.
     */
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