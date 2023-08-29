package duke;

import java.time.LocalDateTime;

/**
 * A Task which has a description and a deadline.
 *
 * The deadline is of type LocalDateTime and is used to specify when the Task is due.
 * Has a tag D.
 */
public class Deadline extends Task{

    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline with description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The date and time when the task is due.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        this.tag = Tag.D;

    }

    /**
     * Changes the format to be more readable.
     *
     * The initial format of LocalDateTime is YYYY-MM-DDTHH:MM:SS.
     * This is turned into Month Day Year, Time, which increases
     * its readability.
     *
     * @param deadline The date and time when the task is due.
     * @return Newly formatted LocalDateTime.
     */
    public String changeFormat(LocalDateTime deadline) {
        return deadline.getMonth().toString() + " " + deadline.getDayOfMonth() + " " +
                deadline.getYear() + ", " + deadline.toLocalTime();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString(){
        return String.format("%s (by: %s)", description, changeFormat(deadline));
    }
}
