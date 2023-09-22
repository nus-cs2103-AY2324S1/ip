package oreo.task;

import oreo.datetime.TimeParser;
import oreo.exception.IllegalDateTimeException;

public class Deadline extends Task {
    private String date;

    private String time;

    /**
     * Constructor for Deadline Class.
     *
     * @param d description of task.
     * @param date deadline date.
     * @param time deadline time.
     * @throws IllegalDateTimeException wrong date time format.
     */
    public Deadline(String description, String date, String time) throws IllegalDateTimeException {
        super(description);
        this.date = (date == null) ? TimeParser.getNextDateOfTime(time) : date;
        this.time = time;
    }

    /**
     * Constructor for Deadline Class. Mainly used for file reading
     *
     * @param d description of task.
     * @param date deadline date.
     * @param time deadline time.
     * @param isComplete whether deadline is completed.
     */
    public Deadline(String description, String date, String time, boolean isComplete) {
        super(description);
        this.date = date;
        this.time = time;
        this.isComplete = isComplete;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeToFile() {
        int mark = isComplete ? 1 : 0;
        String timeString = (this.time == null) ? "" : (", " + TimeParser.parseTimeForFile(this.time));
        String data = 2 + " " + mark + description + "/" + TimeParser.parseDateForFile(this.date) + timeString
                + System.lineSeparator();
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskInEditFormat() {
        String timeString = (this.time == null) ? "" : (", " + TimeParser.parseTimeForFile(this.time));
        return "deadline" + description + " /by " + TimeParser.parseDateForFile(this.date) + timeString;
    }
}
