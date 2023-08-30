package commands;

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
    Storage.save(tasks);
    ui.bye();
  }
}