package oreo.task;

import oreo.datetime.TimeParser;

public class Deadline extends Task {
    String date;

    String time;

    public Deadline(String d, String date, String time) {
        super(d);
        this.date = date;
        this.time = time;
    }

    public Deadline(String d, String date, String time, boolean completed) {
        super(d);
        this.date = date;
        this.time = time;
        this.completed = completed;
    }

    @Override
    public String toString() {
        String marker = "[ ]";
        String timeString;
        timeString = (this.time == null) ? "" : (" " + this.time);
        if (this.completed) marker = "[X]";
        return  "[D]" + marker + " " +
                this.description +
                " (by: " + this.date + timeString + ")\n";
    }

    @Override
    public String writeToFile() {
        int mark = completed ? 1 : 0;
        String data = 2 + " " + mark + description + "/" + TimeParser.parseDateForFile(this.date) +
                TimeParser.parseTimeForFile(this.time) + System.lineSeparator();
        return data;
    }
}
