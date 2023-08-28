package task.taskdatetime;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public abstract class TaskDateTime implements Serializable {


    public static final String OUTPUT_FORMAT = "MMM dd yyyy HH:mm";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_FORMAT);

    public TaskDateTime() {
        // default constructor
    }
    public static TaskDateTime read(String dateTimeAsString) {
        try {
            return new ParseableTaskDateTime(dateTimeAsString);
        } catch (DateTimeParseException e) {
            return new UnparseableTaskDateTime(dateTimeAsString);
        }
    }

    public abstract String getDateTimeAsString();
    private static class ParseableTaskDateTime extends  TaskDateTime {

        LocalDateTime date;
        public String getDateTimeAsString() {
            return this.date.format(TaskDateTime.DATE_TIME_FORMATTER);
        }
        protected ParseableTaskDateTime (String dateTimeAsString)  throws DateTimeParseException {
            this.date = LocalDateTime.parse(dateTimeAsString);
        }

    }

    private static class UnparseableTaskDateTime extends  TaskDateTime {

        String dateTimeAsString;

        public String getDateTimeAsString() {
            return this.dateTimeAsString;
        }
        protected UnparseableTaskDateTime(String dateTimeAsString) {
            this.dateTimeAsString = dateTimeAsString;
        }

    }
}
