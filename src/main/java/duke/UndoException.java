package duke;

public class UndoException extends DukeException{
  public UndoException(int totalChanges) {
    super("You have made " + totalChanges + " new changes in this session, pls enter a value between 0 and " + totalChanges);
  }
}
