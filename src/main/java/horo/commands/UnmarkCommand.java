package horo.commands;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.data.TaskList;

public class UnmarkCommand extends Command {
  private static final String NAME = "unmark";
  private static final String REGEX = "^unmark ([0-9]+)";
  private static final String DISPLAY_FORMAT = "unmark <number>";

  private Integer index;

  public UnmarkCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    index = Integer.parseInt(validateAndParse(input).group(1)) - 1;
  }

  public void execute(TaskList taskList, Ui ui, Storage storage) throws HoroException {
    taskList.markTaskNotDone(index);
    storage.updateTaskData(taskList);

    ui.horoOutput("Task marked as not done");
  }
}
