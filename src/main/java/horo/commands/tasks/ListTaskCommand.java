package horo.commands.tasks;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;

public class ListTaskCommand extends Command {

  public static final String DISPLAY_FORMAT = "list task";
  private static final String NAME = "list task";
  private static final String REGEX = "^list task";

  public ListTaskCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    validateAndParse(input);
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    ui.horoOutput(storage.getTaskList().toString());
  }
}
