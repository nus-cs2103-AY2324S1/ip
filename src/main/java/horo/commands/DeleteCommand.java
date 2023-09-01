package horo.commands;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.data.TaskList;

public class DeleteCommand extends Command {
  private static final String NAME = "delete";
  private static final String REGEX = "^delete ([0-9]+)";
  private static final String DISPLAY_FORMAT = "delete <number>";

  private Integer index;

  public DeleteCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    index = Integer.parseInt(validateAndParse(input).group(1)) - 1;
  }

  public void execute(TaskList taskList, Ui ui, Storage storage) throws HoroException {
    taskList.removeTask(index);
    storage.updateTaskData(taskList);

    ui.horoOutput("Removed task");
  }
}
