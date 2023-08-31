package Commands;

import Exceptions.DukeException;
import TaskPackages.TaskList;
import Utility.Storage;
import Utility.Ui;

public class MarkDonenessCommand extends Command {

  private boolean doneness;
  private int index;

  public MarkDonenessCommand(boolean doneness, int index) {
    this.doneness = doneness;
    this.index = index;
  }

  public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
    ui.print(tasklist.setDoneness(doneness, index));
  }

}
