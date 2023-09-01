import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private final Date by;

    private final SimpleDateFormat presentationFormat = new SimpleDateFormat("MMM dd yyyy kk");
    private final SimpleDateFormat saveFormat = new SimpleDateFormat("dd/MM/yyyy kk");

    public Deadline(String name, Date by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                presentationFormat.format(this.by));
    }

    @Override
    public String toFormat() {
        return String.format(
                "D|%s|%s|%s",
                super.getName(),
                super.isDone() ? "X" : " ",
                saveFormat.format(this.by));
    }
}
