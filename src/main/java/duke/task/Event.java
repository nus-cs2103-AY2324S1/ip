package duke.task;

import duke.exception.KoraException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    //private String byTime;

    LocalDateTime endTime;
    LocalDateTime startTime;
    public Event(String details, String startTime, String endTime) throws KoraException {
        super(details);
        super.setTaskType(TaskType.E.toString());
        try {
            this.startTime = LocalDateTime.parse(startTime, saveFormatter);
            this.endTime = LocalDateTime.parse(endTime, saveFormatter);
        } catch (DateTimeException e) {
            throw new KoraException("The date format should be yyyy-MM-dd HH-mm!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KoraException("Please ensure there is no space before to!");
        }
    }
    DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm");
    DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public String getTime() {
        return startTime.format(saveFormatter) + "to " + endTime.format(saveFormatter);
    }

    @Override
    public String saveFormat() {
        String output;
        output = super.saveFormat() + "/ " + startTime.format(saveFormatter) + "/ " + endTime.format(saveFormatter);
        return output;
    }

    @Override
    public String toString() {
        String output;
        output = super.toString() + "(from: " + startTime.format(outFormatter) + " to: " + endTime.format(outFormatter) + ")";
        return output;
    }
}
