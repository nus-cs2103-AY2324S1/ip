package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindTaskCommand extends Command {
  public FindTaskCommand(TaskList taskList, ParseInfo parseInfo) {
    super(taskList, parseInfo);
  }

  @Override
  public void execute() throws CommandError {
    var tasks = this.taskList.findTask(this.parseInfo.getArgument());
    if (tasks.size() == 0) {
      Ui.printText("No tasks found");
      return;
    }
    List<String> lines = new ArrayList<>();
    lines.add("Here are the matching tasks in your list:");
    for (var task : tasks) {
      lines.add(task.toString());
    }
    Ui.printText(lines.toArray(String[]::new));
  }
}
