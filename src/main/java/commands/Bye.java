package commands;

import java.io.IOException;
import java.util.Scanner;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class Bye extends Command {
  Scanner scanner;

  public Bye(Ui ui, TaskList tasks, Scanner scanner) {
    super(ui, tasks);
    this.scanner = scanner;
  }

  public void execute() {
    scanner.close();
    try {
      Storage.save(tasks);
    } catch (IOException e) {
      this.ui.error("Filepath issue");
    } catch (Exception e) {
      this.ui.error("Failed to save");
    }
    this.ui.bye();
  }
}