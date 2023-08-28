package commands;

import tasks.TaskList;
import utility.PrintUtility;

import java.util.List;

public class MarkTaskCommand implements ICommand {
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
      tasks.setTaskStatus(i - 1, true);
      PrintUtility.printText("Nice! I've marked this task as done:",
          tasks.getTask(i - 1).toString());
    } catch (NumberFormatException e) {
      PrintUtility.printText("Invalid index given");
    }
  }
}
