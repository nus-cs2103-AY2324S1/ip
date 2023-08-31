package duke.task;

public class Todo extends Task{
    /**
     * the type Icon
     */
    private String type = "T";

    /**
     * constructor for duke.task.Event duke.task
     * @param description the text stored
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * override the toString method
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    @Override
    public String toDataString() {
        return this.type + " / " + super.toDataString();
    }
}
