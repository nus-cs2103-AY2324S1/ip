package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.Task;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;

public class DeleteTaskCommand extends Command {
  public DeleteTaskCommand(TaskList taskList, ParseInfo parseInfo) {
    super(taskList, parseInfo);
  }

  @Override
  public void execute() throws CommandError {
    if (this.parseInfo.hasNoArgument()) throw new CommandError("Missing task index");
    try {
      int i = Integer.parseInt(this.parseInfo.getArgument());
      Task task = this.taskList.getTask(i - 1);
      this.taskList.removeTask(i - 1);
      Ui.printText(
          "Noted. I've removed this task:",
          task.toString(),
          String.format("Now you have %d cyrus.tasks in the list.", this.taskList.size())
      );
    } catch (NumberFormatException e) {
      throw new CommandError("Invalid task index: must be integer");
    } catch (IndexOutOfBoundsException e) {
      throw new CommandError("Invalid task index: index out of bounds");
    }
  }
}
