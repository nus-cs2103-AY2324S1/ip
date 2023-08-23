public class Deadline extends Task {
    private String deadline;
    /**
     * Constructor for the Todo class.
     *
     * @param name The name of the todo task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.name + " (by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + this.name + " (by: " + this.deadline + ")";
        }
    }
}
