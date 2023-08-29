package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.Deadline;
import cyrus.tasks.Task;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;
import cyrus.utility.DateUtility;

import java.time.LocalDate;
import java.util.HashMap;

public class AddDeadlineCommand extends Command {
  public AddDeadlineCommand(TaskList taskList, ParseInfo parseInfo) {
    super(taskList, parseInfo);
  }

  @Override
  public void execute() throws CommandError {
    if (this.parseInfo.hasNoArgument()) throw new CommandError("Deadline is missing a body!");

    HashMap<String, String> options = this.parseInfo.getOptions();
    if (!options.containsKey("by")) throw new CommandError("Invalid deadline format: missing /by");

    String deadlineName = this.parseInfo.getArgument();
    String deadlineBy = this.parseInfo.getOptions().get("by");
    LocalDate deadlineDate = DateUtility.parse(deadlineBy);
    if (deadlineDate == null) {
      throw new CommandError("Invalid deadline format: invalid by string, must be format dd/MM/yyyy");
    }

    Task deadline = new Deadline(deadlineName, deadlineDate);
    this.taskList.addTask(deadline);
    Ui.printAddTask(deadline, this.taskList.size());
  }
}
