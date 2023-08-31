package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class ToDoCommand extends TaskCommand {

  public ToDoCommand(String description, boolean doneness) {
    super(description, doneness);
  }

  @Override
  public void load(TaskList tasklist) {
    tasklist.add(description, doneness);
  }

  @Override
  public void execute(TaskList tasklist, Ui ui, Storage storage) {
    String todoString = tasklist.add(description, doneness);
    ui.print(String.format("I've added this task:\n%s\nNow you have %d tasks in the list.", todoString,
        tasklist.getSize()));
  }
}
