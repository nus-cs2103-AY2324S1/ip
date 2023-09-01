import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate ddl;

    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public Deadline(String description, String ddl) {
        super(description);
        this.ddl = LocalDate.parse(ddl);
    }
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getDdl() + ")";
    }

    public String getDdl() {
        return this.ddl.getMonth().toString() + " " + this.ddl.getDayOfMonth() + " " + this.ddl.getYear();
    }

    public String getFormattedDdl() {
        return ddl.format(formatter);
    }

    @Override
    public String getLog() {
        return "D | " + (isDone? "1" : "0")
                + " | " + this.description
                + " | " + this.getFormattedDdl() + System.lineSeparator();
    }
}

