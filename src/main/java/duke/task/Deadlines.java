package duke.task;

import java.time.LocalDateTime;
import duke.main.DateFormatter;

public class Deadlines extends Task {
    protected LocalDateTime endDate;

    private static final DateFormatter DF = new DateFormatter();

    public Deadlines (String description, String endDate) {
        super(description);
        this.endDate = DF.stringToDate(endDate);
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String printTask() {
        return "[" + this.getTypeIcon() + "]" + this.getStatusIcon() + this.description + " (by: " + DF.dateToString(this.endDate) + ")";
    }
}
