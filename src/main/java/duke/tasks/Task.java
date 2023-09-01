package duke.tasks;

public abstract class Task {
  protected String description;
  protected boolean doneness;

  protected Task(String description, boolean doneness) {
    this.description = description;
    this.doneness = doneness;
  }

  /**
  * Returns the icon to use for the status.
  * 
  * 
  * @return the icon to use for the status or blank if task is not done.
  */
  protected String getStatusIcon() {
    return (doneness ? "X" : " ");
  }

  /**
  * Returns whether or not the task is done.
  * 
  * 
  * @return whether or not the task is done.
  */
  protected boolean getIsDone() {
    return this.doneness;
  }

  /**
  * Sets the status of the task as done or not done.
  * 
  * @param doneness true if done, false if not done.
  */
  protected void setDoneness(boolean doneness) {
    this.doneness = doneness;
  }

  /**
  * Returns a string representation of this Task. The string is formatted as "[ ][StatusIcon] Description"
  * 
  * @return a string representation of this Task.
  */
  public String toString() {
    return String.format("[ ][%s] %s", this.getStatusIcon(), this.description);
  }


  /**
   * Returns a string representation of this Task. The format is   # Doneness # Description.
   * Note that this is different from toString() as it is used for encoding data in the file.
   * 
   * 
   * @return a string representation of this Task for storage in the file.
   */
  public String toFileString() {
    return String.format("  # %d # %s", (doneness ? 1 : 0), this.description);
  }
}
