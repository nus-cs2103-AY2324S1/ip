package horo.commands.tasks;

import java.util.ArrayList;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.tasks.Task;

public class ListTaskCommand extends Command {

  public static final String DISPLAY_FORMAT = "list task";
  private static final String NAME = "list task";
  private static final String REGEX = "^list task";

  public ListTaskCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    validateAndParse(input);
  }

  public void execute(Ui ui, Storage storage) throws HoroException {

    StringBuilder sb = new StringBuilder("-----Tasks-----\n");

    ArrayList<Task> tasks = storage.getTaskList().getTasks();

    for (int i = 0; i < tasks.size(); i++) {
      sb.append((i + 1) + ". " + tasks.get(i) + "\n");
    }

    ui.horoOutput(sb.toString());
  }
}
