/**
 * Handles command actions.
 */
public abstract class DudeCommand {
  /**
   * Executes command.
   *
   * @param taskList TaskList to operate on.
   * @param ui       Ui to use.
   * @param storage  Storage to operate on.
   * @throws DudeException If command execution fails.
   */
  public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DudeException;

  /**
   * Returns true if program should exit after command.
   *
   * @return True if program should exit after command is executed; false otherwise.
   */
  public abstract boolean isExit();
}
