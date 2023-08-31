package duke.command;

import duke.exception.DukeException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.TaskList;

public class FindCommand extends Command {
  private String search;

  public FindCommand(Printer out, TaskList taskList, FileIO file, String search) {
    super(out, taskList, file);
    this.search = search;
  }

  @Override
  public void action() {
    if (search.equals("")) {
      throw new DukeException("The argument of find cannot be empty");
    }

    out.print(taskList.filter(search));
  }
}
