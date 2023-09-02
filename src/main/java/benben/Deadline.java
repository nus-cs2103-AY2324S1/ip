package benben;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of task that has a specific date to be completed
 */
public class Deadline extends Task {
    /**
     * The deadline by which the task should be completed
     */
    protected LocalDate ddl;

    /**
     * The Formatter sued to parse the string into a LocalDateTime
     */
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description of the task
     * @param ddl         the ddl by which it should be completed
     * @throws BenBenException if the deadline cannot be parsed
     */
    public Deadline(String description, String ddl) throws BenBenException{
        super(description);
        try {
            this.ddl = LocalDate.parse(ddl);
        } catch (DateTimeParseException e) {
            throw new BenBenException("The date and time is of the wrong format! Please use yyyy-MM-dd");
        }

    }
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getDdl() + ")";
    }

    /**
     * Gets the deadline
     *
     * @return the string representation of the deadline
     */
    public String getDdl() {
        return this.ddl.getMonth().toString() + " " + this.ddl.getDayOfMonth() + " " + this.ddl.getYear();
    }

    /**
     * Gets formatted ddl.
     *
     * @return the formatted ddl
     */
    public String getFormattedDdl() {
        return ddl.format(formatter);
    }

    @Override
    public String getLog() {
        return "D | " + (isDone? "1" : "0")
                + " | " + this.description
                + " | " + this.getFormattedDdl() + System.lineSeparator();
    }

    @Override
    public boolean equals(Object task) {
        if (task == this) {
            return true;
        }

        if (!(task instanceof Deadline)) {
            return false;
        }

        Deadline t = (Deadline) task;

        return t.getLog().equals(this.getLog());
    }
}

