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
     * This method gives the string representation of a deadline task when it is in a list
     *
     * @return The String representation of a Deadline Task
     */
    @Override
    public String showTaskinList() {
        return "[D]" + super.showTaskinList() + "(" + "by: " + this.by + ")";
    }

    @Override
    public String printList() {
        return "D | " + super.printList() + " | " + this.by;
    }

    public Deadline reschedule(String by) {
        return new Deadline(this.showTask(), by);
    }

    public Deadline reschedule(LocalDateTime byDateTime) {
        return new Deadline(this.showTask(), byDateTime);
    }


}
