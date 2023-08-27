package duke;

/**
 * A superclass exception where all other duke exceptions derived from.
 */
public class DukeException extends Exception {

  private String msg;

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
