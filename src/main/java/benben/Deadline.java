package benben;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate ddl;

    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public Deadline(String description, String ddl) throws BenBenException{
        super(description);
        try {
            this.ddl = LocalDate.parse(ddl);
        } catch (DateTimeParseException e) {
            throw new BenBenException("The date and time is of the wrong format! Please use yyyy-MM-dd");
        }

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

    @Override
    public boolean equals(Object task) {
        if (task == this) {
            return true;
        }

        if (!(task instanceof Deadline)) {
            return false;
        }

        Deadline t = (Deadline) task;

        return t.getLog().equals(this.getLog());
    }
}

