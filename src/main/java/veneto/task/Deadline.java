package veneto.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /* fields */
    protected LocalDate deadline;

    /* Constructors */
    /**
     * create a new Deadline Task
     * @param description the description of the task
     * @param deadline the deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * create a new Deadline task from storage file
     * @param description the description of the task
     * @param mark if the task is marked
     * @param deadline the deadline of the task
     */
    public Deadline(String description, int mark, String deadline) {
        super(description, mark != 0);
        this.deadline = LocalDate.parse(deadline);
    }

    /* Methods */
    /**
     * explanation of the task
     * @return return task details
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ ")";
    }

    /**
     * generate texts for storage
     * @return String of the task data
     */
    @Override
    public String saveToString() {
        return "deadline," + super.saveToString() + "," + deadline;
    }
}
