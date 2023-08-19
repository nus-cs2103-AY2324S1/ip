public class DukeException extends Exception {
  public DukeException(String errorMessage) {
    super(errorMessage);
  }
  public DukeException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }
}
