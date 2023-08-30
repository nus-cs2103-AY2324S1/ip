package commands;

import utils.TaskList;
import utils.Ui;

public abstract class Command {
  protected Ui ui;
  protected TaskList tasks;

  protected Command(Ui ui, TaskList tasks) {
    this.ui = ui;
    this.tasks = tasks;
  }

  public abstract void execute();
}
