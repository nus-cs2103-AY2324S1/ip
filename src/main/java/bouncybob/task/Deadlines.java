package bouncybob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the BouncyBob application.
 */
public class Deadlines extends Task {
    private String datetime;
    private LocalDateTime parsedDatetime;

    /**
     * Constructs a new Deadline task with the given name and datetime.
     *
     * @param name     The name of the task.
     * @param datetime The datetime of the deadline.
     */
    public Deadlines(String name, String datetime) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.parsedDatetime = LocalDateTime.parse(datetime, formatter);
        this.datetime = datetime;
    }

    /**
     * Returns the symbol representing the deadline task type.
     *
     * @return "D" for deadline tasks.
     */
    @Override
    public String getSymbol() {
        return "D";
    }

    /**
     * Returns the description of the deadline task.
     *
     * @return The description, which includes the name and the formatted datetime.
     */
    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDatetime = this.parsedDatetime.format(formatter);
        String description = String.format("%s (by: %s)", super.getName(), formattedDatetime);
        return description;
    }

    /**
     * Converts the deadline task to its file storage format.
     *
     * @return The string representation of the deadline task in file storage format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + datetime;
    }
}
