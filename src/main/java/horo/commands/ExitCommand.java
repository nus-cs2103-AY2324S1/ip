package horo.commands;

import horo.HoroException;
import horo.Storage;
import horo.Ui;

public class ExitCommand extends Command {
  private static final String NAME = "bye";
  private static final String REGEX = "^bye";
  private static final String DISPLAY_FORMAT = "bye";

  public ExitCommand() {
    super(NAME, REGEX, DISPLAY_FORMAT);
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    ui.horoOutput("See U!");
    ui.handleExit();
  }
}
