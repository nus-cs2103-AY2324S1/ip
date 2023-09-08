package dude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import dude.exception.InvalidTaskDataException;

/**
 * Task with a deadline.
 */
public class DeadlineTask extends Task {
    /**
     * Deadline
     */
    protected LocalDateTime deadline;

    /**
     * Constructor for Deadline task.
     *
     * @param description Description of task.
     * @param deadline    Deadline for task.
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor for deadline task, specifying completion status.
     *
     * @param description Description of task.
     * @param isDone      Boolean representing task completion status.
     * @param deadline    Deadline for task.
     */
    public DeadlineTask(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Parses save file data into a deadline task instance.
     *
     * @param data Line from save file.
     * @return Task instance.
     * @throws InvalidTaskDataException If data is not in the expected format.
     */
    public static DeadlineTask fromData(String data) throws InvalidTaskDataException {
        /*
          expected format:
          completed: 1, incomplete: 0
          deadline: D || 1/0 || deadline || description
        */
        String[] splitData = data.split(DELIMITER_REGEX, 4);

        if (splitData.length < 4) {
            throw new InvalidTaskDataException();
        }
        String taskType = splitData[0];
        String taskCompleted = splitData[1];
        String taskDeadlineString = splitData[2];
        LocalDateTime taskDeadline = LocalDateTime.parse(taskDeadlineString);
        String taskDescription = splitData[3];
        if (!taskCompleted.equals("1") && !taskCompleted.equals("0")) {
            throw new InvalidTaskDataException();
        }
        boolean isCompleted = taskCompleted.equals("1");
        return new DeadlineTask(taskDescription, taskDeadline, isCompleted);
    }

    /**
     * Returns Deadline task formatted with type and status icon.
     *
     * @return Task formatted as a string.
     */
    @Override
    public String toString() {
      return String.format("<D>%s (BY: %s)", super.toString(),
              deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)));
    }

    /**
     * Parses deadline task instance into save file string data.
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toData() {
     /*
       expected format:
       completed: 1, incomplete: 0
       deadline: D || 1/0 || deadline || description
     */
        String taskCompleted = isDone ? "1" : "0";
        return String.join(DELIMITER, "D", taskCompleted, deadline.toString(), description) + "\n";
    }
}
