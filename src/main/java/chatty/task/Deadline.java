package chatty.task;

/**
 * Class that handles the Deadline task
 */
public class Deadline extends Task {

    private final String deadline;

    /**
     * ontructor for Deadline class that calls the constructor of the parent class to create a Task object
     * @param taskDescription the deadline task to be added
     * @param deadline THe deadline of the task to be added
     */
    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    /**
     * Return the String representation of the object
     * @return String representation of the object
     */
    @Override
    public String toString() {
        String status = (this.isDone) ? "[D][X] " : "[D][ ] ";
        String task = String.format("%s (by: %s)", this.task, this.deadline);
        return status + task;
    }
}
