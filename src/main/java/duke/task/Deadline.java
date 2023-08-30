package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
     protected LocalDate byDate;

    /**
     * Constructor.
     *
     * @param description Description of the task.
     * @param byDate Deadline of the task.
     */
     public Deadline(String description, LocalDate byDate) {
         super(description);
         this.byDate = byDate;
     }


     @Override
     public String toString() {
         return "[D]" + super.toString() + " (by: " +
                 byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

     }

    @Override
    public String saveString() {
         return super.saveString() + "/" + byDate.toString();
    }
}
