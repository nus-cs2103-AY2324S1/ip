package taskClasses;

import exception.InvalidDateTimeException;
import utils.DateTimeUtils;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime date;
    public Deadline(String description, String date) throws InvalidDateTimeException {
        super(description, "D");
        this.date =  DateTimeUtils.stringToLocalDateTime(date);
        this.addedTaskDescription();
    }
    @Override
    public String getDetails(){
        return String.format(" (by: %s)",
                DateTimeUtils.localDateTimeToString(this.date));
    }

    public String getDBString() {
        return String.format("%s | %s | %s | %s",
                "D",this.isDone() ? "1" : "0",
                this.description,
                DateTimeUtils.localDateTimeToString(this.date));
    }
}
