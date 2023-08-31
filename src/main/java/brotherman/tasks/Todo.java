package brotherman.tasks;

/**
 * Represents a task in the task list
 */
public class Todo extends Task{

    /**
     * Constructor for Task
     * @param description Description of the task
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns the type of the task
     * @return Type of the task
     */
    public String type() {
        return "T";
    }



    /**
     * Returns the description of the task
     * @return Description of the task
     */
    @Override
    public String storeText() {
        return String.format("%s|%s|%s", this.type(), this.isDone, this.description);
    }

    /**
     * Returns the description of the task
     * @return Description of the task
     */
    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s", type, super.toString() );
    }
}
