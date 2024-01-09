package duke.task;


import duke.exception.DukeException;
import duke.exception.DukeNoDateException;
import duke.exception.DukeNoDescriptionException;
import duke.processors.TimeProcessor;

/**
 * An event class contains start time and end time.
 */
public class Event extends Task {

    private final int EVENT_PREFIX = 5;

    /**
     * A constructor for event class.
     *
     * @param Description The description of event.
     * @throws DukeException if the description doesn't fit the standard.
     *
     */
    public Event(String Description) throws DukeException {
        super(Description);
        if (Description.split("\\s+").length == 1) {
            throw new DukeNoDescriptionException("Event");
        }

        this.Description = getContent(Description);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }

    /**
     * A constructor for reading from txt file.
     *
     * @param content the content of the event.
     * @param isDone the status of the event.
     */
    public Event(String content, boolean isDone) {
        super(content);
        this.isDone = isDone;
    }


    private String getContent(String Description)
            throws DukeException {
        String time;
        int index = Description.indexOf("/");

        if (!Description.contains("/") ||
                !Description.substring(index + 1).contains("/")) {
            throw new DukeNoDateException("Event");
        }

        if (Description.split("\\s+").length == 1) {
            throw new DukeNoDescriptionException("Event");
        }

        Description = Description.replaceAll("\\s+", " ");
        index = Description.indexOf("/");
        String content = Description.substring(EVENT_PREFIX + 1, index);
        int lastIndex = Description.substring(index + 1).indexOf("/");
        lastIndex += (index + 1);

        String startTime = Description.substring(index + 6, lastIndex - 1);
        String endTime = Description.substring(lastIndex + 4);
        if (startTime.contains(" ")) {
            int spaceIndex = startTime.indexOf(" ");
            String date = TimeProcessor.StringToDate(startTime.substring(0, spaceIndex));
            startTime = "from: " + date + " "
                    + TimeProcessor.StringToDate(startTime.substring(spaceIndex + 1));
        } else {
            startTime = "from: " + TimeProcessor.StringToDate(startTime);
        }

        time = startTime + " to: " + TimeProcessor.StringToDate(endTime);
        assert !time.isEmpty(): "time should not be empty";
        return content + "(" + time + ")";
    }

    /**
     * A string containing the information about the event.
     *
     * @return a string containing info of the event.
     */
    public String toString() {
        return "[E]" + super.toString();
    }
}
