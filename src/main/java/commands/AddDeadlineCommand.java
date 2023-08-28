package commands;

import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import utility.PrintUtility;

import java.util.List;

public class AddDeadlineCommand implements ICommand {
  @Override
  public void execute(List<String> parts) {
    TaskList tasks = TaskList.getInstance();

    if (parts.size() == 1) {
      PrintUtility.printText("Deadline is missing a body!");
      return;
    }

    int byIdx = parts.indexOf("/by");
    if (byIdx == -1) {
      PrintUtility.printText("Invalid deadline format: missing /by");
      return;
    }

    String deadlineName = String.join(" ", parts.subList(1, byIdx));
    String deadlineBy = String.join(" ", parts.subList(byIdx + 1, parts.size()));

    Task deadline = new Deadline(deadlineName, deadlineBy);
    tasks.addTask(deadline);
    PrintUtility.printAddTask(deadline);
  }
}
