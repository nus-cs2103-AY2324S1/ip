/**
 * Deadline is the main class for Deadline task used by the Sidtacphi bot.
 */
public class Deadline extends Task {
    private String deadline = "";

    /**
     * Constructor for the Deadline class.
     * 
     * @param name
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Shows whether the task is completed and the name of the task.
     * 
     * @return Type of task, completion state of task and the name of task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadline + ")";     
    }
}
