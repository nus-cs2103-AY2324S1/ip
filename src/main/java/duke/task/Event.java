package duke.task;

import duke.exception.DukeDateOutOfRange;
import duke.exception.DukeNoDateException;
import duke.exception.DukeNoDescriptionException;
import duke.processors.TimeProcessor;

/**
 * A event class contains start time and end time.
 */
public class Event extends Task {
    /**
     * A constructor for event class
     * @param Description The description of event
     * @throws DukeNoDescriptionException if the description is empty
     * @throws DukeNoDateException if the description does not contain
     *                             the two dates
     * @throws DukeDateOutOfRange if the date given is wrong
     */
    public Event(String Description) throws DukeNoDescriptionException,
            DukeNoDateException,  DukeDateOutOfRange {
        super(Description);
        if (Description.split("\\s+").length == 1) {
            throw new DukeNoDescriptionException("Event");
        }

        this.Description = getContent(Description);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }

    /**
     * A constructor for reading from txt file
     * @param content the content of the event
     * @param isDone the status of the event
     */
    public Event(String content, boolean isDone) {
        super(content);
        this.isDone = isDone;
    }


    private String getContent(String Description) throws DukeNoDateException,
            DukeDateOutOfRange {
        String time;
        int index = Description.indexOf("/");
        String content = Description.substring(6, index);
        int lastIndex = Description.substring(index + 1).indexOf("/");
        if (lastIndex != -1) {
            lastIndex += (index + 1);
        }

        if (lastIndex == index || Description.length() - lastIndex <  7 || lastIndex == -1) {
            throw new DukeNoDateException("Event");
        }

        String startTime = Description.substring(index + 6, lastIndex - 1);
        String endTime = Description.substring(lastIndex + 4);
        if (startTime.contains(" ")) {
            int spaceIndex = startTime.indexOf(" ");
            String date = TimeProcessor.StringToDate(startTime.substring(0, spaceIndex));
            startTime = "from: " + date + " " + startTime.substring(spaceIndex + 1);
        } else {
            startTime = "from: " + TimeProcessor.StringToDate(startTime);
        }

        time = startTime + " to: " + TimeProcessor.StringToDate(endTime);
        return content + "(" + time + ")";
    }

    /**
     * A string containing the information about the event.
     * @return a string containing info of the event
     */
    public String toString() {
        return "[E]" + super.toString();
    }
}
