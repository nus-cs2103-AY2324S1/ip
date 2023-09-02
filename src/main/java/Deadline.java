import java.util.Date;

public class Deadline extends Task {
    private final Date by;

    public Deadline(String name, Date by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                DateUtils.presentFormat(this.by));
    }

    @Override
    public String toFormat() {
        return String.format(
                "D|%s|%s|%s",
                super.getName(),
                super.isDone() ? "X" : " ",
                DateUtils.saveFormat(this.by));
    }
}
