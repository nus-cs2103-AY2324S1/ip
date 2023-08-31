package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;
import duke.exceptions.DukeException;

public class MarkDonenessCommand extends Command {

  private boolean doneness;
  private int index;

  public MarkDonenessCommand(boolean doneness, int index) {
    this.doneness = doneness;
    this.index = index;
  }

  @Override
  public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
    ui.print(tasklist.setDoneness(doneness, index));
  }

}
