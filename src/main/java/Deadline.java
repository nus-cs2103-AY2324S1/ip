/**
 * Tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task{
    //TODO

    final static String SYMBOL = "D";
    protected String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }


    @Override
    public String getType() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.by + ")";
    }
}
