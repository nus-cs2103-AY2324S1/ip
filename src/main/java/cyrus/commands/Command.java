package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;

/**
 * Represents a general command to operate on a {@code TaskList} and a given {@code ParseInfo}
 */
public abstract class Command {
  /**
   * {@code TaskList} made {@code protected final} since sub-classes should be able to access it.
   */
  protected final TaskList taskList;
  /**
   * {@code ParseInfo} made {@code protected final} since sub-classes should be able to access it.
   */
  protected final ParseInfo parseInfo;

  public Command(TaskList taskList, ParseInfo parseInfo) {
    this.taskList = taskList;
    this.parseInfo = parseInfo;
  }

  /**
   * Behavior of command that operates on the given {@code TaskList} and {@code ParseInfo}.
   * @throws CommandError if there is a validation error with the {@code ParseInfo}
   */
  public abstract void execute() throws CommandError;

  /**
   * Executes the command behavior and prints any errors thrown.
   */
  public final void run() {
    try {
      this.execute();
    } catch (CommandError e) {
      Ui.printText(e.getMessage());
    }
  }
}
