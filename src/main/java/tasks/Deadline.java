package tasks;

/**
 * The class that will create a deadline line task.
 * This class extends from the tasks class.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructs the method.
     * @param name The name of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        super.setType("D");
        this.deadline = deadline;
    }

    /**
     * Represent the task in string.
     *
     * @return The syntax that will be shown to the user.
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + deadline + ")";
    }
}
