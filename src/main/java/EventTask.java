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
   * @param description Description of task.
   */
  public EventTask(String description, String startTime, String endTime) {
    super(description);
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
}
