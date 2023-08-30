import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Joe {
  private TaskList tasks;
  private Storage storage;
  private Ui ui;

  public Joe(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    try {
      tasks = storage.readTasks();
    } catch (FileNotFoundException e) {
      ui.print("No saved task list was found.");
      tasks = new TaskList();
    } catch (IOException | JoeException e) {
      ui.print(e.getMessage());
      tasks = new TaskList();
    }
  }

  public void run() {
    ui.newLine();
    ui.greet();
    ui.newLine();
    boolean isExit = false;
    while (!isExit) {
      try {
        String input = ui.readCommand();
        Command c = Parser.parse(input);
        c.execute(tasks, ui, storage);
        isExit = c.isExit();
      } catch (JoeException e) {
        ui.print(e.getMessage());
      } finally {
        ui.newLine();
      }
    }
  }

  public static void main(String[] args) {
    new Joe("joe.txt").run();
  }
}
