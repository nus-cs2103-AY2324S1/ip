package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class UnmarkCommand implements Command {
  public static final String UNMARK_PATTERN = "^(unmark)\\s+\\d+$";

  private int pos;

  public UnmarkCommand(int pos) {
    this.pos = pos;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    if (pos > tasks.size() || pos <= 0) {
      System.out.println("Invalid index. Please enter again.");
    } else {
      tasks.unmark(pos);
      Storage.refresh(tasks);
    }
  }
}
