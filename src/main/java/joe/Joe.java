package joe;

import java.io.FileNotFoundException;
import java.io.IOException;
import joe.commands.Command;
import joe.exceptions.JoeException;

/**
 * Represents the Joe chatbot.
 */
public class Joe {
  private TaskList tasks;
  private final Storage storage;
  private final Ui ui;

  /**
   * Constructs a Joe object with the specified file path for task storage.
   *
   * @param filePath The file path for storing tasks.
   */
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

  /** Starts the Joe application. */
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

  /**
   * The main entry point of the Joe application.
   *
   * @param args The command-line arguments.
   */
  public static void main(String[] args) {
    new Joe("joe.txt").run();
  }
}
