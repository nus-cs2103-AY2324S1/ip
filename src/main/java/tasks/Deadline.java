package tasks;

/**
 * A child class to Task, this in particular is a deadline task.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * A constructor method.
     * @param name the name of the task.
     * @param deadline the deadline of the task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        super.setType("D");
        this.deadline = deadline;
    }

    /**
     * * A method that will represent the task.
     *
     * @return the syntax that will be shown to the user.
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + deadline + ")";
    }
}
