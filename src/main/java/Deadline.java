import java.awt.*;

public class Deadline extends Task{
     protected String by;

    /**
     * Constructor.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
     public Deadline(String description, String by) {
         super(description);
         this.by = by;
     }

     @Override
     public String toString() {
         return "[D]" + super.toString() + " (by: " + by + ")";
     }

    @Override
    public String saveString() {
         return super.saveString() + "/" + by;
    }
}
