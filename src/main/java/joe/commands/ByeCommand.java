package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;

public class ByeCommand extends Command {
  public ByeCommand() {
    this.isExit = true;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.print("Bye. Hope to see you again soon!");
    ui.exit();
    storage.saveToFile(tasks);
  }
}
