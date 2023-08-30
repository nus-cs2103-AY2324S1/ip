package commands;

import tasks.Task;
import utils.TaskList;
import utils.Ui;

public class Mark extends Command {
  private Task task;

  public Mark(Ui ui, TaskList tasks, int index) {
    super(ui, tasks);
    this.task = this.tasks.get(index - 1);
  }

  public void execute() {
    this.task.setDone();
    this.ui.markTask(this.task);
  }
}
