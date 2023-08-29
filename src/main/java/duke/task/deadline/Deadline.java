import java.time.LocalDateTime;

/**
 * Deadline class is a task that contains a name and an end date
 */
public class Deadline extends Task{

    private String by;

    private LocalDateTime bydateTime;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, LocalDateTime bydateTime) {
        super(name);
        this.bydateTime = bydateTime;
        this.by = Task.printDate(bydateTime);
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

}
