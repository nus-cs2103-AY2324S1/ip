public class Deadline extends Task {
    private final String end;

    public Deadline(String name, String end) {
        super(name);
        this.end = end;
    }

    /**
     * Generates the formatted representation of the deadline task.
     * The format includes the task status, task type, description, and end time.
     *
     * @return The formatted representation of the deadline task.
     */
    @Override
    public String getTask() {
        return String.format("[%s][D] %s (by: %s)", super.checkDone(), super.getName(), end);
    }
}
