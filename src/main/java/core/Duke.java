package core;

import Commands.Command;
import parser.CommandParser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Duke {
  private static Storage storage;
  private static TaskList taskList;
  private static Ui ui;
  private static String filepath;

  public Duke(String filepath) {
    Duke.filepath = filepath;
    ui = new Ui();
    storage = new Storage(filepath);
    taskList = new TaskList();
  }

  public static void run() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        Command c = CommandParser.parse(fullCommand);
        c.execute(taskList, ui, storage);
      } catch (Exception e) {
        ui.showError(e.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    new Duke("data/tasks.txt").run();
  }
}
