package commands;

import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import utility.DateUtility;
import utility.PrintUtility;

import java.time.LocalDate;
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

    LocalDate deadlineDate = DateUtility.parse(deadlineBy);
    if (deadlineDate == null) {
      PrintUtility.printText("Invalid deadline format: invalid date string, must be format " +
          "dd/MM/yyyy");
      return;
    }
    Task deadline = new Deadline(deadlineName, deadlineDate);
    tasks.addTask(deadline);
    PrintUtility.printAddTask(deadline);
  }
}
