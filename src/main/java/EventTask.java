public class EventTask extends Task {

  /**
   * Start time
   */
  protected String startTime;
  /**
   * End time
   */
  protected String endTime;

  /**
   * Constructor for Event task.
   *
   * @param description Description of task
   * @param startTime   Start time of event
   * @param endTime     End time of event
   */
  public EventTask(String description, String startTime, String endTime) {
    super(description);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Constructor for Event task, specifying completion status.
   *
   * @param description Description of task
   * @param startTime   Start time of event
   * @param endTime     End time of event
   * @param isDone      Boolean representing task completion status
   */
  public EventTask(String description, String startTime, String endTime, boolean isDone) {
    super(description, isDone);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Gets Event task formatted with type and status icon
   *
   * @return Task formatted as a string.
   */
  @Override
  public String toString() {
    return String.format("<E>%s (FROM: %s, TO: %s)", super.toString(), this.startTime, this.endTime);
  }


  /**
   * Parses save file data into an event task instance.
   *
   * @param data line from save file
   * @return Task instance
   * @throws InvalidTaskDataException if data is not in the expected format
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
    String taskStart = splitData[2];
    String taskEnd = splitData[3];
    String taskDescription = splitData[4];
    if (!taskCompleted.equals("1") && !taskCompleted.equals("0")) {
      throw new InvalidTaskDataException();
    }
    boolean isCompleted = taskCompleted.equals("1");
    return new EventTask(taskDescription, taskStart, taskEnd, isCompleted);
  }

  /**
   * Parses event task instance into save file string data
   *
   * @return Task data as string
   */
  public String toData() {
     /*
       expected format:
       completed: 1, incomplete: 0
       event: E || 1/0 || start || end || description
     */
    String taskCompleted = this.isDone ? "1" : "0";
    return String.join(DELIMITER, "E", taskCompleted,
      this.startTime, this.endTime, this.description) + "\n";
  }
}
