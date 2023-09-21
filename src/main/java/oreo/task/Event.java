package oreo.task;

import oreo.datetime.TimeParser;
import oreo.exception.IllegalDateTimeException;

public class Event extends Task {
    private String fromDate;
    private String fromTime;
    private String toDate;
    private String toTime;
    public Event(String d, String fromDate, String fromTime,
                 String toDate, String toTime) throws IllegalDateTimeException {
        super(d);
        this.fromDate = (fromDate == null) ? TimeParser.getTodayString() : fromDate;
        this.fromTime = fromTime;
        this.toDate = (toDate == null) ? this.fromDate : toDate;
        this.toTime = toTime;
        TimeParser.checkValidEventDate(this.fromDate, this.toDate);
        if (this.fromDate.equals(this.toDate) && this.fromTime != null
        && this.toTime != null) {
            TimeParser.checkValidEventTime(this.fromTime, this.toTime);
        }
    }

    public Event(String d, String fromDate, String fromTime,
                 String toDate, String toTime , boolean completed) {
        super(d);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
        this.isComplete = completed;
    }

    @Override
    public String toString() {
        String marker = "☐";
        if (this.isComplete) {
            marker = "☑";
        }
        String fromDateString = (this.fromDate == null) ? "" : (" " + this.fromDate);
        String fromTimeString = (this.fromTime == null) ? "" : (" " + this.fromTime);
        String toDateString = (this.toDate == null) ? "" : (" " + this.toDate);
        String toTimeString = (this.toTime == null) ? "" : (" " + this.toTime);
        return  "\uD83C\uDD74:" + " " + this.description
                + " (from: " + fromDateString + fromTimeString
                + " to: " + toDateString + toTimeString + ") "
                + marker + "\n";
    }

    @Override
    public String writeToFile() {
        int mark = isComplete ? 1 : 0;
        String fromTimeString = (this.fromTime == null) ? "" : (", " + TimeParser.parseTimeForFile(this.fromTime));
        String toTimeString = (this.toTime == null) ? "" : (", " + TimeParser.parseTimeForFile(this.toTime));
        String data = 3 + " " + mark + description + "/" + TimeParser.parseDateForFile(this.fromDate) + fromTimeString
                + "/" + TimeParser.parseDateForFile(this.toDate) + toTimeString + System.lineSeparator();
        return data;
    }

    @Override
    public String getTaskInEditFormat() {
        String fromTimeString = (this.fromTime == null) ? "" : (", " + TimeParser.parseTimeForFile(this.fromTime));
        String toTimeString = (this.toTime == null) ? "" : (", " + TimeParser.parseTimeForFile(this.toTime));
        return "event" + description + " /from " + TimeParser.parseDateForFile(this.fromDate) + fromTimeString
                + " /to " + TimeParser.parseDateForFile(this.toDate) + toTimeString;
    }
}
