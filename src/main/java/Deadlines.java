import java.time.LocalDate;
public class Deadlines extends Task {

    protected String byTime;
    public Deadlines(String description, String by) {
        super(description);
        this.byTime = by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTime + ")";
    }
}