package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;

public abstract class Command {
  public final TaskList taskList;
  public final ParseInfo parseInfo;

  public Command(TaskList taskList, ParseInfo parseInfo) {
    this.taskList = taskList;
    this.parseInfo = parseInfo;
  }

  public abstract void execute() throws CommandError;

  public final void run() {
    try {
      this.execute();
    } catch (CommandError e) {
      Ui.printText(e.getMessage());
    }
  }
}
