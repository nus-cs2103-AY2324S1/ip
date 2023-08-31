import Exceptions.DukeException;
import Tasks.TaskList;
import Utilities.*;
import Commands.Command;

public class Duke {

  private Storage storage;
  private TaskList tasklist;
  private Ui ui;

  public Duke(String filepath) {
    this.ui = new Ui();
    this.storage = new Storage(filepath);
    try {
      tasklist = storage.load();
    } catch (DukeException e) {
      ui.showLoadingError();
      tasklist = new TaskList();
    }
  }

  private void run() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullcommand = ui.readCommand();
        Command c = Parser.parse(fullcommand);
        c.execute(tasklist, ui, storage);
        isExit = c.isExit();
      } catch (DukeException e) {
        ui.showError(e.getMessage());
      } finally {
        ui.showLine();
      }
    }
    ui.showGoodbye();
    storage.writeToFile(tasklist);
  }

  public static void main(String[] args) {
    new Duke("data/duke.txt").run();
  }
}
