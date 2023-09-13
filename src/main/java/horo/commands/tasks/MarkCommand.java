package horo.commands.tasks;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.tasks.TaskList;

public class MarkCommand extends Command {

  public static final String DISPLAY_FORMAT = "mark <number>";
  private static final String NAME = "mark";
  private static final String REGEX = "^mark ([0-9]+)";

  private Integer index;

  public MarkCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    index = Integer.parseInt(validateAndParse(input).group(1)) - 1;
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    TaskList taskList = storage.getTaskList();
    taskList.markTaskDone(index);
    storage.updateTaskData(taskList);

    ui.horoOutput("Task marked as done");
  }
}
