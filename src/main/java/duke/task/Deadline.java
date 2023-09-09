package duke.task;

import duke.filemanagement.DateTimeDetector;

/**
 * Represents a task with deadline.
 */
public class Deadline extends Task {

    protected String by;
    private final DateTimeDetector detector = new DateTimeDetector();

    /**
     * Constructor to build a task with description as input.
     * @param description Describes the task.
     */
    public Deadline(String description, String by) {
        this(description, false, by);
    }

    /**
     * Constructor to build a task with description and isDone as input.
     * @param description Describes the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public void update(String attributeToUpdate, String contentToUpdate) {
        switch (attributeToUpdate) {
            case "description":
                this.description = contentToUpdate;
            case "by":
                this.by = contentToUpdate;
        }
    }


    /**
     * Prints out the description of the task and its status.
     * @return A string that shows the task's description and status.
     */
    @Override
    public String toString() {
        String d = detector.format(by);
        return "[D]" + super.toString() + " (by: " + d + ")";
    }
}
