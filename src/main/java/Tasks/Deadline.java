package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class Deadline extends Task {
    private Temporal deadlineTemporal;
    private DateTimeFormatter printFormatter;
    private DateTimeFormatter saveFormatter;
    public Deadline(String name, boolean isDone, Temporal deadlineTemporal) {
        super(name, isDone);
        this.deadlineTemporal = deadlineTemporal;
        this.printFormatter = deadlineTemporal instanceof LocalDateTime
                ? DateTimeFormatter.ofPattern("HHmm, dd LLL, yyyy")
                : DateTimeFormatter.ofPattern("dd LLL, yyyy");
        this.saveFormatter = deadlineTemporal instanceof LocalDateTime
                ? DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")
                : DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    @Override
    public String toString() {
        return "[D]"
                    + super.toString()
                        + String.format(" (by: %s)", printFormatter.format(this.deadlineTemporal));
    }
    public String toString(boolean isWritten) {
        String completionStr = super.isDone() ? "1" : "0";
        return "D"
                + " | "
                    + completionStr
                        + " | "
                            + super.getName()
                                + " | "
                                    + saveFormatter.format(this.deadlineTemporal);
    }
}
