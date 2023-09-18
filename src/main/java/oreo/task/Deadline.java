package oreo.task;

import oreo.datetime.TimeParser;

public class Deadline extends Task {
    private String date;

    private String time;

    public Deadline(String d, String date, String time) {
        super(d);
        this.date = date;
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
        String timeString;
        timeString = (this.time == null) ? "" : (" " + this.time);
        return  "DEADLINE:" + " " + this.description
                + " (by: " + this.date + timeString + ") "
                + marker + "\n";
    }

    @Override
    public String writeToFile() {
        int mark = isComplete ? 1 : 0;
        String data = 2 + " " + mark + description + "/" + TimeParser.parseDateForFile(this.date)
                + TimeParser.parseTimeForFile(this.time) + System.lineSeparator();
        return data;
    }

    @Override
    public String getTaskInEditFormat() {
        return "deadline" + description + " /by " + TimeParser.parseDateForFile(this.date)
                + TimeParser.parseTimeForFile(this.time);
    }
}
