package duke.task;

import duke.exception.KoraException;
import duke.parser.DateTimeParser;

/**
 * Subclass of Task class. Stores information about the deadline task details.
 */
public class Deadline extends Task {
    private DateTimeParser dtParser;
    private final String saveString;
    private final String outString;
    public Deadline(String details, String time) throws KoraException {
        super(details);
        super.setTaskType(TaskType.D.toString());
        byTime = time;
        dtParser = new DateTimeParser();
        saveString = dtParser.saveFormatted(byTime);
        outString = dtParser.outFormatted(byTime);
    }

    @Override
    public String getTime() {
        return saveString;
    }

    @Override
    public String saveFormat() {
        String output;
        output = super.saveFormat() + " / " + saveString;
        return output;
    }

    @Override
    public String toString() {
        String output;
        output = super.toString() + " (" + "by: " + outString + ")";
        return output;
    }
}
