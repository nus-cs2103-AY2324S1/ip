package duke;

import duke.Parser;
import duke.Task;
import duke.exception.DetailsUnknownException;

import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Constructor for the Event class.
     * Extract the start and end details of the event task
     *
     * @param task the task description of the task entered by user
     * @param dateTimeDetails the details of the event task such as its start and end timing
     * @throws DetailsUnknownException throws details unknown exception if the start or end details of the event task is missing
     */
    public Event(String task, String dateTimeDetails) throws DetailsUnknownException {
        super(task);
        try {
            String[] timeArr = Parser.splitEventDateTime(dateTimeDetails); // return [start time, end time]
            this.startDateTime = Parser.formatDate(timeArr[0]);
            this.endDateTime = Parser.formatDate(timeArr[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DetailsUnknownException();
        }
    }

    /**
     * A getter to get the start details of this event task
     *
     * @return the start details of the task
     */
    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    /**
     * A getter to get the end details of this event task
     *
     * @return the end details of the task
     */
    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }


    @Override
    public String toString() {
        String formattedStart = Parser.dateToString(startDateTime);
        String formattedEnd = Parser.dateToString(endDateTime);
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedEnd + ") ";
    }
}