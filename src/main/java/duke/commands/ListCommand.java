package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class ListCommand extends Command {

  public ListCommand() {
  };

  @Override
  public void execute(TaskList tasklist, Ui ui, Storage storage) {
    ui.print(tasklist.toString());
  }
}
