package commands;

import java.io.IOException;
import java.util.Scanner;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class Bye extends Command {
  private Scanner scanner;
  private Boolean[] running;

  public Bye(Ui ui, TaskList tasks, Scanner scanner, Boolean[] running) {
    super(ui, tasks);
    this.running = running;
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
    this.running[0] = false;
    this.ui.bye();
  }
}