package commands;

import utils.TaskList;
import utils.Ui;

public class List extends Command {
  private TaskList tasks;
  private Ui ui;

  public List(TaskList tasks, Ui ui) {
    this.tasks = tasks;
    this.ui = ui;
  }

  public void execute() {
    this.ui.listTasks(this.tasks);
  }
}
