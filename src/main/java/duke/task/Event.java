package duke.task;

import duke.filemanagement.DateTimeDetector;

/**
 * Represents a task that is an Event.
 */
public class Event extends Task {

    protected String from;
    protected String to;
    private final DateTimeDetector detector = new DateTimeDetector();

    /**
     * Constructor to build a task with description as input.
     * @param description Describes the task.
     */
    public Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    /**
     * Constructor to build a task with description as input.
     * @param description Describes the task.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public void update(String attributeToUpdate, String contentToUpdate) {
        switch (attributeToUpdate) {
            case "description":
                this.description = contentToUpdate;
            case "from":
                this.from = contentToUpdate;
            case "to":
                this.to = contentToUpdate;
        }
    }

    /**
     * Prints out the description of the task and its status.
     * @return A string that shows the task's description and status.
     */
    @Override
    public String toString() {
        String f = detector.format(from);
        String t = detector.format(to);
        return "[E]" + super.toString() + " (from: " + f + " to: " + t + ")";
    }
}
