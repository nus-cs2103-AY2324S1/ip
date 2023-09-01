package Tasks;

import java.time.LocalDate;

/**
 * A child class to Task, this in particular is a deadline task.
 */
public class Deadline extends Task{
    private String deadline;
    private LocalDate deadDate;
    public Deadline(String name, String deadline) {
        super(name);
        this.type = "D";
        this.deadline = deadline;
    }

    /**
     * * A method that will represent the task.
     *
     * * @return the syntax that will be shown to the user.
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + (deadline.isEmpty() ? deadDate : deadline) +")";
    }
}
