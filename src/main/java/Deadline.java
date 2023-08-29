import java.time.LocalDateTime;

public class Deadline extends Task{
    private LocalDateTime by;

    public Deadline(String name, String by) {
        super(name);
        this.by = DateParser.convertStringToDateTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.convertDateTimeToString(this.by) + ")";
    }

    public String convertTaskToString() {
        return "D | " + (super.isDone() ? "1" : "0") + " | " + super.getName() + " | "
                + DateParser.convertDateTimeToString(this.by) ;
    }
}
