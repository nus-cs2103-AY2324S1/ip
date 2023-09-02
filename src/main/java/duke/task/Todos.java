package duke.task;

import duke.task.Task;

/** Class to represent the task set by user*/
public class Todos extends Task {

    /**
     * The constructor for Deadlines
     * @param description The description of the task
     */
    public Todos(String description) {
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
