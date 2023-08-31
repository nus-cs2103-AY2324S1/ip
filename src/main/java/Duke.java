import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.Ui;

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
    try {
      storage.writeToFile(tasklist);
    } catch (DukeException e) {
      System.err.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    new Duke("data/duke.txt").run();
  }
}
