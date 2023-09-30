package veneto.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Deadline extends Task {
    /* fields */
    protected LocalDate deadline;

    /* Constructors */
    /**
     * create a new Deadline Task
     * @param description the description of the task
     * @param deadline the deadline of the task
     */
    public Deadline(String description, String deadline) throws DateTimeParseException {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deadline deadline1 = (Deadline) o;
        boolean sameDescription = super.equals(o);
        boolean sameDeadline = deadline.equals(deadline1.deadline);

        return sameDescription && sameDeadline;
    }
}
