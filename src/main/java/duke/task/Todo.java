package duke.task;

/** Class to represent the task set by user*/
public class Todo extends Task {

    /**
     * The constructor for Deadline
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }

    @Override
    public String printTask() {
        return "[" + this.getTypeIcon() + "]" + this.getStatusIcon() + this.description;
    }
}
