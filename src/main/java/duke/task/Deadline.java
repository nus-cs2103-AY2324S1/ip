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

    /**
     * Class constructor of Deadline.
     * @param details Task description.
     * @param time Task due date.
     * @throws KoraException When there is error parsing the string to DateTime.
     */
    public Deadline(String details, String time) throws KoraException {
        super(details);
        super.setTaskType(TaskType.D.toString());
        byTime = time;
        dtParser = new DateTimeParser();
        saveString = dtParser.saveFormatted(byTime);
        outString = dtParser.outFormatted(byTime);
    }

    /**
     * Returns formatted date time message.
     * @return String formatted date time.
     */
    @Override
    public String getTime() {
        return saveString;
    }

    /**
     * Returns formatted date time to save in Storage.
     * @return String formatted date time.
     */
    @Override
    public String saveFormat() {
        String output;
        output = super.saveFormat() + " / " + saveString;
        return output;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        String output;
        output = super.toString() + " (" + "by: " + outString + ")";
        return output;
    }
}
