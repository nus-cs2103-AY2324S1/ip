package robert.task;

/**
 * The stub class for tasks.
 *
 * @author Lee Zhan Peng
 */
public class TaskStub extends Task {

    /** The description of the task */
    private final String description;

    /** The indication on whether it is marked */
    private final boolean isDone;

    /**
     * Constructs a generic task stub.
     */
    public TaskStub() {
        super("TASKSTUB", true);
        this.description = "TASKSTUB";
        this.isDone = true;
    }

    /**
     * A getter of the description.
     *
     * @return string "TASKSTUB".
     */
    public String getDescription() {
        return "TASKSTUB";
    }

    /**
     * A getter of the description.
     *
     * @return string "X".
     */
    public String getStatusIcon() {
        return "X"; // mark done task with X
    }

    /**
     * Returns the string representation of a generic task.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return "[X] " + this.description;
    }
}
