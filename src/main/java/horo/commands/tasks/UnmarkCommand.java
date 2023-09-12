package horo.commands.tasks;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.tasks.TaskList;

public class UnmarkCommand extends Command {

  public static final String DISPLAY_FORMAT = "unmark <number>";
  private static final String NAME = "unmark";
  private static final String REGEX = "^unmark ([0-9]+)";

  private Integer index;

  public UnmarkCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    index = Integer.parseInt(validateAndParse(input).group(1)) - 1;
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    TaskList taskList = storage.getTaskList();
    taskList.markTaskNotDone(index);
    storage.updateTaskData(taskList);

    ui.horoOutput("Task marked as not done");
  }
}
