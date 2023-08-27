public class Deadline extends Task {
    private String deadline;
    /**
     * Constructor for the Todo class.
     *
     * @param name The name of the todo task.
     * @param deadline The deadline of the task.
     * @param done Whether the task is marked done or not.
     */
    public Deadline(String name, String deadline, boolean done) {
        super(name, done);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.name + " By: " + this.deadline;
        } else {
            return "[D][ ] " + this.name + " By: " + this.deadline;
        }
    }
}
