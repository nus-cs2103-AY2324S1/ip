package chatbot.tasks;

/**
 * Represents an todo
 */
public class Todo extends Task {
    /**
     * Constructor for todo
     * @param description Description of todo
     */
    public Todo (String description){
        super(description);
    }

    /**
     * Returns the formatted string representation of the todo
     * @return Formatted string representation of the todo
     */
    @Override
    public String store(){
        return String.format("T | %s | %s", this.isDone, this.description);
    }

    /**
     * Returns the formatted string representation of the todo
     * @return Formatted string representation of the todo
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
