package oreo.task;

import oreo.datetime.TimeParser;
import oreo.exception.IllegalDateTimeException;

public class Deadline extends Task {
    private String date;

    private String time;

    public Deadline(String d, String date, String time) throws IllegalDateTimeException {
        super(d);
        this.date = (date == null) ? TimeParser.getNextDateOfTime(time) : date;
        this.time = time;
    }

    public Deadline(String d, String date, String time, boolean completed) {
        super(d);
        this.date = date;
        this.time = time;
        this.isComplete = completed;
    }

    @Override
    public String toString() {
        String marker = "☐";
        if (this.isComplete) {
            marker = "☑";
        }
        String dateString = (this.date == null) ? "" : (" " + this.date);
        String timeString = (this.time == null) ? "" : (" " + this.time);
        return  "\uD83C\uDD73:" + " " + this.description
                + " (by: " + dateString + timeString + ") "
                + marker + "\n";
    }

    @Override
    public String writeToFile() {
        int mark = isComplete ? 1 : 0;
        String timeString = (this.time == null) ? "" : (", " + TimeParser.parseTimeForFile(this.time));
        String data = 2 + " " + mark + description + "/" + TimeParser.parseDateForFile(this.date) + timeString
                + System.lineSeparator();
        return data;
    }

    @Override
    public String getTaskInEditFormat() {
        String timeString = (this.time == null) ? "" : (", " + TimeParser.parseTimeForFile(this.time));
        return "deadline" + description + " /by " + TimeParser.parseDateForFile(this.date) + timeString;
    }
}
