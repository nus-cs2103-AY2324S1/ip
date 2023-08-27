import java.time.LocalDateTime;

public class Deadline extends Task{

    protected LocalDateTime limit;

    private Deadline(String title, LocalDateTime limit) {
        super(title);
        this.limit = limit;
    }

    /**
     * Adds a new Deadline task to the list of tasks.
     * @param title Title of task.
     * @param limit Deadline of task.
     * @return Task object created.
     */
    public static Task addDeadline(String title, LocalDateTime limit) {
        Task deadline = new Deadline(title, limit);
        taskList.add(deadline);
        return deadline;
    }

    @Override
    public String toString() {
        String time = String.format(" (by: %s)", limit.format(displayFormat));
        return "[D]" + super.toString() + time;
    }

    /**
     * Convert Deadline task to a string for storing in data file.
     * @return Formatted string with data for Deadline task.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + limit.format(dataFormat);
    }
}
