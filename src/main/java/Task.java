public class Task {
  protected boolean isDone;
  protected String description;
  /**
   * Delimiter regex for save file data
   */
  public static final String DELIMITER = "  ||  ";
  /**
   * Delimiter regex for save file data
   */
  public static final String DELIMITER_REGEX = "  \\|\\|  ";

  /**
   * Constructor for task.
   *
   * @param description Description of task
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Constructor for task, specifying completion status.
   *
   * @param description Description of task
   * @param isDone      Boolean representing task completion status
   */
  public Task(String description, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
  }

  /**
   * Gets text status icon of task's completion status.
   *
   * @return space if not done; X if done.
   */
  public String getStatusIcon() {
    return (this.isDone ? "X" : " ");
  }

  /**
   * Gets task formatted with status icon
   *
   * @return Task formatted as a string. e.g. [X] completed task
   */
  @Override
  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), this.description);
  }

  /**
   * Marks task as done.
   */
  public void markAsDone() {
    this.isDone = true;
  }

  /**
   * Marks task as not done.
   */
  public void markAsNotDone() {
    this.isDone = false;
  }


  /**
   * Parses save file data into a Task instance.
   *
   * @param data line from save file
   * @return Task instance
   * @throws InvalidTaskDataException if data is not in the expected format
   */
  public static Task fromData(String data) throws InvalidTaskDataException {
     /*
       expected format:
       completed: 1, incomplete: 0
       todo: T || 1/0 || description
     */
    String[] splitData = data.split(DELIMITER_REGEX, 3);

    if (splitData.length < 3) {
      throw new InvalidTaskDataException();
    }
    String taskType = splitData[0];
    String taskCompleted = splitData[1];
    String taskDescription = splitData[2];
    if (!taskCompleted.equals("1") && !taskCompleted.equals("0")) {
      throw new InvalidTaskDataException();
    }
    boolean isCompleted = taskCompleted.equals("1");
    return new Task(taskDescription, isCompleted);
  }


  /**
   * Parses To Do task instance into save file string data
   *
   * @return Task data as string
   */
  public String toData() {
     /*
       expected format:
       completed: 1, incomplete: 0
       todo: T || 1/0 || description
     */
    String taskCompleted = this.isDone ? "1" : "0";
    return String.join(DELIMITER, "T", taskCompleted, this.description) + "\n";
  }
}
