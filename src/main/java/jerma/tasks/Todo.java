package jerma.tasks;

public class Todo extends Task {
    /**
     * Constructor for a Todo Task
     * 
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
        this.symbol = "T";
    }

    /**
     * Returns string representation of Todo Task for saving purposes
     * 
     * @return String representation of Todo Task for saving purposes
     */
    @Override
    public String save() {
        return String.format("%s|%s", this.symbol, super.save());
    }

    /**
     * Returns string representation of Todo Task
     * 
     * @return String representation of Todo Task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", this.symbol, super.toString());
    }
}
