package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.KoraException;

public class Deadline extends Task {
    private DateTimeFormatter outFormatter;
    private DateTimeFormatter saveFormatter;
    public Deadline(String details, String time) throws KoraException {
        super(details);
        super.setTaskType(TaskType.D.toString());
        outFormatter = DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm");
        saveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            byTime = LocalDateTime.parse(time, saveFormatter);
        } catch (DateTimeParseException e) {
            throw new KoraException("The date format should be yyyy-MM-dd HH-mm!");
        }

    }

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
