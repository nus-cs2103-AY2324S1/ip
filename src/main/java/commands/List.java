package commands;

import utils.TaskList;
import utils.Ui;

public class List extends Command {
  public List(Ui ui, TaskList tasks) {
    super(ui, tasks);
  }

  public void execute() {
    this.ui.listTasks(this.tasks);
  }
}
