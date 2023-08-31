package horo.commands;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.data.TaskList;

public class MarkCommand extends Command {
  private static final String NAME = "mark";
  private static final String REGEX = "^mark ([0-9]+)";
  private static final String DISPLAY_FORMAT = "mark <number>";

  private Integer index;

  public MarkCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    index = Integer.parseInt(validateAndParse(input).group(1)) - 1;
  }

  public void execute(TaskList taskList, Ui ui, Storage storage) throws HoroException {
    taskList.markTaskDone(index);
    storage.updateTaskData(taskList);

    ui.horoOutput("Task marked as done");
  }
}
