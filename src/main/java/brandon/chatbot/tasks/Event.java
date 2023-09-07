package brandon.chatbot.tasks;

import brandon.chatbot.common.DukeException;

public class Event extends Task {
    private String startTime;
    private String endTime;
    public Event(String title, String startTime, String endTime) throws DukeException {
        super(title);
        if (startTime.isBlank()) {
            throw new DukeException("    Start time of an event cannot be blank...\n--------------------------------");
        }
        this.startTime = startTime;
        if (endTime.isBlank()) {
            throw new DukeException("    End time of an event cannot be blank...\n--------------------------------");
        }
        this.endTime = endTime;
    }

@Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
