package duke.task;

import duke.filemanagement.DateTimeDetector;
import duke.userio.InvalidUserInputException;

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

    /**
     * Updates attribute with input content if it has the input attribute.
     * @param attributeToUpdate Attribute to be updated.
     * @param contentToUpdate Content to overwrite current attribute's value.
     * @throws InvalidUserInputException If attributeToUpdate is any of attributes of a Deadline task.
     */
    @Override
    public void update(String attributeToUpdate, String contentToUpdate) throws InvalidUserInputException {
        switch (attributeToUpdate) {
            case "description":
                this.description = contentToUpdate;
            case "by":
                this.by = contentToUpdate;
            default:
                throw new InvalidUserInputException();
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
