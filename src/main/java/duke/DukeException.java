package duke;

/**
 * A superclass exception where all other duke exceptions derived from.
 */
public class DukeException extends Exception {

  private String msg;

  /**
   * Creates a new exception with the error message.
   *
   * @param msg the error message that will be displayed
   */
  DukeException(String msg) {
    super(msg);
    this.msg = msg;
  }

  /**
   *  Returns a string representation of the object.
   *
   * @return a string representation of the object
   */
  public String toString() {
    return msg;
  }

}
