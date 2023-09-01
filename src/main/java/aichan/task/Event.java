package aichan.task;

import aichan.AiChanException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event with its start date and end date.
 * Inherits from Task.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs the Event object.
     * Initializes description and date od start and end, set it as have not done.
     *
     * @param strs Array of string which first element is description,
     *             second element is start date, third element is end date.
     * @throws AiChanException If the format of any date is incorrect.
     */
    public Event(String[] strs) throws AiChanException {
        // inside this array has 3 elements
        // first is taskName, second element is start, third element is end time
        super(strs[0]);
        // assume that strs[1] follow the format 25/12/2019 1800
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.start = LocalDateTime.parse(strs[1], formatter);
            this.end = LocalDateTime.parse(strs[2], formatter);
        } catch (DateTimeParseException e) {
            throw new AiChanException("Please enter the date and time with this format: 25/12/2019 1800");
        }
    }

    /**
     * Returns the string representation of the event object.
     *
     * @return A string indicates whether the event is done followed by its description, date of start and end.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d yyyy HHmm"); // Dec 25 2019 1800
        return String.format("[E]%s (from: %s to: %s)",
                             super.toString(), this.start.format(formatter2), this.end.format(formatter2));
    }

    /**
     * Returns the line of the event object to be saved in the file.
     *
     * @return A string indicates whether the event is done followed by its description, date of start and end.
     */
    @Override
    public String toFileLine() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return String.format("E | %s | %s - %s",
                             super.toFileLine(), this.start.format(formatter), this.end.format(formatter));
    }
}
