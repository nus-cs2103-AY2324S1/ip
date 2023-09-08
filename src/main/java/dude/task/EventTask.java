package dude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import dude.exception.InvalidTaskDataException;

/**
 * Task with a start and end date/time.
 */
public class EventTask extends Task {

    /**
     * Start time.
     */
    protected LocalDateTime startTime;
    /**
     * End time.
     */
    protected LocalDateTime endTime;

    /**
     * Constructor for Event task.
     *
     * @param description Description of task.
     * @param startTime   Start time of event.
     * @param endTime     End time of event.
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor for Event task, specifying completion status.
     *
     * @param description Description of task.
     * @param startTime   Start time of event.
     * @param endTime     End time of event.
     * @param isDone      Boolean representing task completion status.
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

  /**
   * Parses save file data into an event task instance.
   *
   * @param data Line from save file.
   * @return Task instance.
   * @throws InvalidTaskDataException If data is not in the expected format.
   */
  public static EventTask fromData(String data) throws InvalidTaskDataException {
     /*
       expected format:
       completed: 1, incomplete: 0
       event: E || 1/0 || start || end || description
     */
        String[] splitData = data.split(DELIMITER_REGEX, 5);

        if (splitData.length < 5) {
            throw new InvalidTaskDataException();
        }
        String taskType = splitData[0];
        String taskCompleted = splitData[1];
        String taskStartString = splitData[2];
        LocalDateTime taskStart = LocalDateTime.parse(taskStartString);
        String taskEndString = splitData[3];
        LocalDateTime taskEnd = LocalDateTime.parse(taskEndString);
        String taskDescription = splitData[4];
        if (!taskCompleted.equals("1") && !taskCompleted.equals("0")) {
            throw new InvalidTaskDataException();
        }
        boolean isCompleted = taskCompleted.equals("1");
        return new EventTask(taskDescription, taskStart, taskEnd, isCompleted);
    }

  /**
   * Gets Event task formatted with type and status icon.
   *
   * @return Task formatted as a string.
   */
  @Override
  public String toString() {
    return String.format("<E>%s (FROM: %s | TO: %s)", super.toString(),
            startTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
            endTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)));
  }

  /**
   * Parses event task instance into save file string data.
   *
   * @return {@inheritDoc}
   */
  @Override
  public String toData() {
     /*
       expected format:
       completed: 1, incomplete: 0
       event: E || 1/0 || start || end || description
     */
        String taskCompleted = isDone ? "1" : "0";
        return String.join(DELIMITER, "E", taskCompleted,
                startTime.toString(), endTime.toString(), description) + "\n";
    }
}
