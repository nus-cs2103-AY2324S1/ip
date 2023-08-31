package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline task
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
     * Stored as DEADLINE,{task description},{by}
     *
     * @return DEADLINE,{task description},{by}
     */
    @Override
    public String getStored() {
        return String.join(Task.SEP, new String[] { "DEADLINE", this.getTask(),
                this.isComplete() ? "1" : "0", this.by.toString() });
    }

}
