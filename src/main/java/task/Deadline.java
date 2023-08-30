package task;
import exception.KoraException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    //private String byTime;
    public Deadline(String details, String time) {
        super(details);
        super.setTaskType(TaskType.D.toString());
        //byTime = time;

        byTime = LocalDateTime.parse(time, saveFormatter);
    }

    DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm");
    DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public String getTime() {
        return byTime.format(saveFormatter);
    }

    @Override
    public String saveFormat() {
        String output;
        output = super.saveFormat() + "/ " + byTime.format(saveFormatter);
        return output;
    }

    @Override
    public String toString() {
        String output;
        output = super.toString() + "(" + "by: " + byTime.format(outFormatter) + ")";
        return output;
    }
}
