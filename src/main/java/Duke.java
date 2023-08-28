import java.io.File;
import java.util.List;

public class Duke {
  
  private Storage storage;
  private TaskList tasks;
  private Ui ui;

  public Duke(String name, String projectName, File mainFile) {
    this.storage = new Storage(projectName, mainFile);
    this.tasks = this.storage.loadTasks();
    this.ui = new VerboseUi(name);
  }

  public void run() {
    ui.greet();
    boolean isExit = false;
    while (!isExit) {
      try {
        Command cmd = Parser.parse(ui.readCommand());
        cmd.execute(tasks, ui, storage);
        isExit = cmd.isExit();
      } catch (DukeException e) {
        ui.print(e.getMessage());
      }
    }
  }

}
