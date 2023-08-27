package duke;

public class DukeException extends Exception {

  private String msg;

  DukeException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public String toString() {
    return msg;
  }

}
