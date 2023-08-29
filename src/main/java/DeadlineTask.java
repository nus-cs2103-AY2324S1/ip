import java.time.LocalDateTime;

public class DeadlineTask extends Task {

    /**
     * The deadline of the task
     */
    private LocalDateTime by;

    /**
     * constructor for DeadlineTask
     *
     * @param by   - the deadline of the task
     * @param task - the description of the task created
     */
    public DeadlineTask(LocalDateTime by, String task) {
        super(task);
        this.by = by;
    }

    /**
     * constructor for DeadlineTask from storage
     *
     * @param by        - the deadline of the task
     * @param task      - the description of the task created
     * @param completed - if completed
     */
    public DeadlineTask(LocalDateTime by, String task, boolean completed) {
        super(task);
        this.by = by;
        if (completed) {
            this.toggleCompleted();
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
        return String.join(Task.SEP, new String[]{"DEADLINE", this.getTask(), this.isCompleted() ? "1" : "0", this.by.toString()});
    }

}
