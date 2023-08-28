package commands;

import tasks.Task;
import tasks.TaskList;
import utility.PrintUtility;

import java.util.List;

public class DeleteTaskCommand implements ICommand {
  @Override
  public void execute(List<String> parts) {

    if (parts.size() == 1) {
      PrintUtility.printText("Missing task index");
      return;
    }

    TaskList tasks = TaskList.getInstance();

    try {
      int i = Integer.parseInt(parts.get(1));
      if ((i - 1) < 0 || (i - 1) >= tasks.size()) {
        PrintUtility.printText("Missing task index");
        return;
      }
      Task task = tasks.getTask(i - 1);
      tasks.removeTask(i - 1);
      PrintUtility.printText(
          "Noted. I've removed this task:",
          task.toString(),
          String.format("Now you have %d tasks in the list.", tasks.size())
      );
    } catch (NumberFormatException e) {
      PrintUtility.printText("Invalid task index");
    }
  }
}
