public class DukeIOBException extends DukeException {
  public DukeIOBException(int idx) {
    super("Task " + idx + " does not exist");
  }
}
