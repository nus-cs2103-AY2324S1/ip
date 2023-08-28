package commands;

import tasks.TaskList;
import utility.PrintUtility;

import java.util.List;

public class UnmarkTaskCommand implements ICommand {
  @Override
  public void execute(List<String> parts) {
    if (parts.size() == 1) {
      PrintUtility.printText("Invalid index given");
      return;
    }

    TaskList tasks = TaskList.getInstance();

    try {
      int i = Integer.parseInt(parts.get(1));
      if ((i - 1) < 0 || (i - 1) >= tasks.size()) {
        PrintUtility.printText("Invalid index given");
        return;
      }
      tasks.setTaskStatus(i - 1, false);
      PrintUtility.printText("OK, I've marked this task as not done yet:",
          tasks.getTask(i - 1).toString());
    } catch (NumberFormatException e) {
      PrintUtility.printText("Invalid index given");
    }
  }
}
