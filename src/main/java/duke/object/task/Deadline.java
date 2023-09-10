package duke.object.task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import duke.command.task.DeadlineCommand;
import duke.util.Formatter;

/**
 * Task with an end date.
 */
public class Deadline extends Task implements Comparable<Deadline> {

    protected LocalDate by;

    /**
     * Constructs Deadline.
     *
     * @param description The user's description of the task.
     * @param by The end date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * @inheritdoc
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Formatter.stringifyDate(this.by));
    }

    /**
     * @inheritdoc
     */
    @Override
    public String toCommand(int idx) {
        return (new DeadlineCommand(Map.ofEntries(
                new SimpleEntry<>("description", this.description),
                new SimpleEntry<>("by", this.by)))).toString()
                + "\n" + super.toCommand(idx) + "\n";
    }

    /**
     * @inheritdoc
     */
    @Override
    public int compareTo(Deadline other) {
        return (int) ChronoUnit.DAYS.between(other.by, this.by);
    }

}
