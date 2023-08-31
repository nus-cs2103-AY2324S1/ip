import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate end;

    public Deadline(String taskDescription, String name, LocalDate end) {
        super(taskDescription, name);
        this.end = end;
    }

    /**
     * Generates the formatted representation of the deadline task.
     * The format includes the task status, task type, description, and end time.
     *
     * @return The formatted representation of the deadline task.
     */
    @Override
    public String getTaskForPrinting() {
        return String.format("[%s][D] %s (by: %s)", super.checkDone(), super.getName(), super.formatDate(end));
    }

    @Override
    public String getTaskDescription() {
        return "deadline " + super.getTaskDescription();
    }
}