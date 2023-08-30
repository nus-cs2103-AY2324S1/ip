package Tasks;

import Utils.DukeDateFormat;

import java.time.LocalDate;

public class Deadline extends Task{

    /** Date that task is to be completed by. */
    private LocalDate by;

    /**
     * Constructor for Tasks.Deadline.
     * @param taskName Name of deadline.
     * @param by Date where the task is due.
     */
    public Deadline(String taskName, int isDone, LocalDate by) {
        super(taskName, isDone);
        this.by = by;
    }

    /**
     * Returns the string representation of the status of the task
     * @return Status of the task
     */
    @Override
    public String getTask() {
        return "Deadline ->" + super.getTask() + " By: " + DukeDateFormat.dateToString(by);
    }

    @Override
    public String toString() {
        return super.toString().replace("/TASK", "deadline ") + "/by " + by.toString();
    }
}
