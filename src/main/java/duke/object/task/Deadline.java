package duke.object.task;

import duke.command.task.DeadlineCommand;
import duke.ui.Ui;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

/**
 * Task with an end date.
 */
public class Deadline extends Task implements Comparable<Deadline> {

    protected LocalDate by;

    /**
     * Constructor for Deadline.
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
        return String.format("[D]%s (by: %s)", super.toString(), Ui.stringifyDate(this.by));
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
