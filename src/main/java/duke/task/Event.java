package duke.task;

import duke.exception.DukeNoDateException;
import duke.exception.DukeNoDescriptionException;
import duke.processors.TimeProcessor;
public class Event extends Task{
    public Event(String Description) throws DukeNoDescriptionException, DukeNoDateException {
        super(Description);
        if (Description.split("\\s+").length == 1) {
            throw new DukeNoDescriptionException("Event");
        }

        this.Description = getContent(Description);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }

    public Event(String content, boolean isDone) {
        super(content);
        this.isDone = isDone;
    }

    private String getContent(String Description) throws DukeNoDateException {
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

    public String toString() {
        return "[E]" + super.toString();
    }
}
