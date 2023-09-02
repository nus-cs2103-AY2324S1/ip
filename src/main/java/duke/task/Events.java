package duke.task;

import java.time.LocalDateTime;
import duke.main.DateFormatter;

public class Events extends Task {
    protected LocalDateTime startDate;

    private static final DateFormatter DF = new DateFormatter();
    protected LocalDateTime endDate;
    public Events (String description, String startDate, String endDate) {
        super(description);
        this.startDate = DF.stringToDate(startDate);
        this.endDate = DF.stringToDate(endDate);
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String printTask() {
        if (this.startDate.toLocalDate().equals(this.endDate.toLocalDate())) {
            return "[" + this.getTypeIcon() + "]" + this.getStatusIcon() + this.description + " (from: " + DF.dateTimeToDate(this.startDate) +
                    " " + DF.dateTimeToTime(this.startDate) + " to: " + DF.dateTimeToTime(this.endDate) + ")";
        }
        return "[" + this.getTypeIcon() + "]" + this.getStatusIcon() + this.description +
                " (from: " + DF.dateToString(this.startDate) + " to: " + DF.dateToString(this.endDate) + ")";
    }


}
