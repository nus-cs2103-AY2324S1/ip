package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;

public class UnmarkTaskCommand extends Command {
  public UnmarkTaskCommand(TaskList taskList, ParseInfo parseInfo) {
    super(taskList, parseInfo);
  }

  @Override
  public void execute() throws CommandError {
    if (this.parseInfo.hasNoArgument()) throw new CommandError("Missing task index");

    try {
      int i = Integer.parseInt(this.parseInfo.getArgument());
      this.taskList.setTaskStatus(i - 1, false);
      Ui.printText("OK, I've marked this task as not done yet:",
          this.taskList.getTask(i - 1).toString());
    } catch (NumberFormatException e) {
      throw new CommandError("Invalid task index: must be integer");
    } catch (IndexOutOfBoundsException e) {
      throw new CommandError("Invalid task index: index out of bounds");
    }
  }
}
