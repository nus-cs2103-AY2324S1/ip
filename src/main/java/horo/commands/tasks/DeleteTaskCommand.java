package horo.commands.tasks;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.tasks.TaskList;

public class DeleteTaskCommand extends Command {

  public static final String DISPLAY_FORMAT = "delete task <number>";
  private static final String NAME = "delete task";
  private static final String REGEX = "^delete task ([0-9]+)";

  private Integer index;

  public DeleteTaskCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    index = Integer.parseInt(validateAndParse(input).group(1)) - 1;
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    TaskList taskList = storage.getTaskList();
    taskList.remove(index);
    storage.updateTaskData(taskList);

    ui.horoOutput("Removed task");
  }
}
