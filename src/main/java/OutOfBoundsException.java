public class OutOfBoundsException extends DukeException {

  public OutOfBoundsException(int index, int taskCount) {
    super(String.format("☹ OOPS!!! %d is out of range. %s", index + 1, Ui.getTaskCount(taskCount)));
  }
  
}
