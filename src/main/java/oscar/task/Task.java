package oscar.task;

import java.io.Serializable;

/**
 * Task superclass that supports todo, deadline and event tasks.
 * Taken from hint.
 */
abstract public class Task implements Serializable {
  private final String description;
  private final String type;
  private boolean isDone;

  /**
   * Public constructor that is invoked by subclasses when creating task.
   */
  public Task(String description, String type) {
    this.description = description;
    this.isDone = false;
    this.type = type;
  }

  /**
   * Checks if task is done and indicates "X" for done and blank otherwise.
   *
   * @return Indicator of task completion.
   */
  public String getStatusIcon() {
    return (this.isDone ? "X" : " "); // mark done task with X
  }

  /**
   * Marks a task as done.
   */
  public void markAsDone() {
    this.isDone = true;
  }

  /**
   * Marks a task as not done.
   */
  public void markAsNotDone() {
    this.isDone = false;
  }

  /**
   * Obtains description of task.
   *
   * @return String description of task.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Formats string representation of task.
   *
   * @return Information of task.
   */
  @Override
  public String toString() {
    return "[" + this.type + "][" + getStatusIcon() + "] " + this.description;
  }
}
