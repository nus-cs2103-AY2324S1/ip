package Commands;

import java.time.LocalDate;
import java.time.LocalTime;

import Tasks.TaskList;
import Utilities.Storage;
import Utilities.Ui;

public class DeadlineCommand extends TaskCommand {

  private LocalDate byDate;
  private LocalTime byTime;

  public DeadlineCommand(String description, boolean doneness, LocalDate byDate, LocalTime byTime) {
    super(description, doneness);
    this.byDate = byDate;
    this.byTime = byTime;
  }

  @Override
  public void load(TaskList tasklist) {
    tasklist.add(this.description, this.doneness, this.byDate, this.byTime);
  }

  @Override
  public void execute(TaskList tasklist, Ui ui, Storage storage) {
    String deadlineString = tasklist.add(this.description, this.doneness, this.byDate, this.byTime);
    ui.print(
        String.format("I've added this deadline:\n%s\nNow you have %d tasks in the list.", deadlineString,
            tasklist.getSize()));
  }
}
