package duke.task;

import duke.exception.KoraException;
import duke.parser.DateTimeParser;

/**
 * Subclass of Task class. Stores information about the event task details.
 */
public class Event extends Task {
    private DateTimeParser dtParser;
    private final String startTime;
    private final String endTime;
    private final String saveStringStart;
    private final String saveStringEnd;
    private final String outStringStart;
    private final String outStringEnd;

    /**
     * Class constructor of Event.
     * @param details Task description
     * @param startTime Task start date time.
     * @param endTime Task end date time.
     * @throws KoraException When there is error parsing the string to DateTime.
     */
    public Event(String details, String startTime, String endTime) throws KoraException {
        super(details);
        super.setTaskType(TaskType.E.toString());
        this.startTime = startTime;
        this.endTime = endTime;
        dtParser = new DateTimeParser();
        saveStringStart = dtParser.saveFormatted(this.startTime);
        saveStringEnd = dtParser.saveFormatted(this.endTime);
        outStringStart = dtParser.outFormatted(this.startTime);
        outStringEnd = dtParser.outFormatted(this.endTime);
    }

    /**
     * Returns formatted date time message.
     * @return String formatted date time.
     */
    @Override
    public String getTime() {
        return saveStringStart + " to " + saveStringEnd;
    }

    /**
     * Returns formatted date time to save in Storage.
     * @return String formatted date time.
     */
    @Override
    public String saveFormat() {
        String output;
        output = super.saveFormat() + " / " + saveStringStart + " / " + saveStringEnd;
        return output;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        String output;
        output = super.toString()
                + " (from: " + outStringStart + " to: " + outStringEnd + ")";
        return output;
    }
}
