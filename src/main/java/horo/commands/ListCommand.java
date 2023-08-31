package horo.commands;

import java.util.ArrayList;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.data.Task;
import horo.data.TaskList;

public class ListCommand extends Command {

  private static final String NAME = "list";
  private static final String REGEX = "^list";
  private static final String DISPLAY_FORMAT = "list";

  public ListCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    validateAndParse(input);
  }

  public void execute(TaskList taskList, Ui ui, Storage storage) throws HoroException {

    StringBuilder sb = new StringBuilder("-----Tasks-----\n");

    ArrayList<Task> tasks = taskList.getTasks();

    for (int i = 0; i < tasks.size(); i++) {
      sb.append((i + 1) + ". " + tasks.get(i) + "\n");
    }

    ui.horoOutput(sb.toString());
  }
}
