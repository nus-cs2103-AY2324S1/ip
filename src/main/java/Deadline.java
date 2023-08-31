/**
 * Deadline class that inherits from Task.
 * 
 * @var deadline Representing deadline.
 * 
 * @author Owen Yeo
 */
public class Deadline extends Task{

    private String deadline;

    /**
     * Constructor for a deadline object.
     * 
     * @param label Descriptor for the task with deadline
     * @param deadline Deadline
     */
    Deadline(String label, String deadline) {
        super(label);
        this.deadline = deadline;
    }

    /**
     * 
     */
    @Override
    public String toSaveString() {
        return "D " + super.toSaveString() + " | " + deadline;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
    
}
