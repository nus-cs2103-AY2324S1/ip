package duke.task.deadline;

import java.time.LocalDateTime;

import duke.task.Task;

/**
 * Deadline class is a task that contains a name and an end date
 */
public class Deadline extends Task {

    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, LocalDateTime byDateTime) {
        super(name);
        this.by = Task.printDate(byDateTime);
    }

    /**
     * Gives the string representation of a deadline task when it is in a list
     *
     * @return The String representation of a Deadline Task
     */
    @Override
    public String showTaskinList() {
        return "[D]" + super.showTaskinList() + "(" + "by: " + this.by + ")";
    }

    /**
     * This method gives the string representation of a Deadline task when it is in the saved list
     *
     * @return The String representation of a Deadline task in the saved list
     */
    @Override
    public String printList() {
        return "D | " + super.printList() + " | " + this.by;
    }

    /**
     * Creates the new Deadline object with the new by time
     *
     * @param by The end time of a deadline in String
     * @return A new Deadline object with new by time
     */
    public Deadline reschedule(String by) {
        return new Deadline(this.showTask(), by);
    }

    /**
     * Creates the new Deadline object with the new DateTime
     *
     * @param byDateTime The end time of a deadline in String in DateTime
     * @return A new Deadline object with new by time in DateTime
     */
    public Deadline reschedule(LocalDateTime byDateTime) {
        return new Deadline(this.showTask(), byDateTime);
    }


}
