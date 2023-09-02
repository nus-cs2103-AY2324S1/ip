package duke.utilities;

/**
 * Class to declare a Deadline task
 */
public class Deadline extends Task {
	
    /** Deadline of a deadline task */
    private String deadline;

    /**
     * Creates new instance of a deadline task
     *
     * @param name Name of task
     * @param deadline Deadline of the task
     */
    public Deadline(String name, String deadline) {
        super(name, Type.DEADLINE, " (by: " + deadline + ")");
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }
}
