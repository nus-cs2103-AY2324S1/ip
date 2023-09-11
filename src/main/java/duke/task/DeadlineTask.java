package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a deadline task in a task manager
 */
public class DeadlineTask extends Task {

    /**
     * The deadline of the task
     */
    private LocalDateTime by;

    /**
     * constructor for duke.task.DeadlineTask
     *
     * @param by   - the deadline of the task
     * @param task - the description of the task created
     */
    public DeadlineTask(LocalDateTime by, String task) {
        super(task);
        this.by = by;
    }

    /**
     * constructor for duke.task.DeadlineTask from storage
     *
     * @param by         - the deadline of the task
     * @param task       - the description of the task created
     * @param isComplete - if completed
     */
    public DeadlineTask(LocalDateTime by, String task, boolean isComplete) {
        super(task);
        this.by = by;
        if (isComplete) {
            this.toggleComplete();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Task.formatDate(this.by) + ")";
    }

    /**
     * returns the stored form of this deadline
     * Stored as DEADLINE{task description}{by}
     *
     * @return DEADLINE{task description}{by}
     */
    @Override
    public String getStored() {
        return String.join(Task.SEP, new String[] { "DEADLINE", this.getTask(),
                this.isComplete() ? "1" : "0", this.by.toString() });
    }

    /**
     * returns the stored form of the task
     *
     * @param days - the range of days of task to be reminded
     * @return true if this task need to be reminded
     */
    @Override
    public boolean isRemind(int days) {
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(days);
        return this.by.isAfter(start.atStartOfDay()) && this.by.isBefore(end.atStartOfDay());
    }

}
