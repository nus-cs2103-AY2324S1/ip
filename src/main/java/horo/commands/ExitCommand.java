package horo.commands;

import java.util.concurrent.TimeUnit;

import horo.HoroException;
import horo.Storage;
import horo.Ui;

public class ExitCommand extends Command {

  public static final String DISPLAY_FORMAT = "bye";
  private static final String NAME = "bye";
  private static final String REGEX = "^bye";

  public ExitCommand() {
    super(NAME, REGEX, DISPLAY_FORMAT);
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    ui.horoOutput("See U!");
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace(System.out);
    }
    ui.handleExit();
  }
}
