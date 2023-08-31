package Commands;

import java.time.LocalDate;
import java.time.LocalTime;

import TaskPackages.TaskList;
import Utility.Storage;
import Utility.Ui;

public class DeadlineCommand extends TaskCommand {

  private LocalDate byDate;
  private LocalTime byTime;

  public DeadlineCommand(String description, boolean doneness, LocalDate byDate, LocalTime byTime) {
    super(description, doneness);
    this.byDate = byDate;
    this.byTime = byTime;
  }

  public void load(TaskList tasklist) {
    tasklist.add(this.description, this.doneness, this.byDate, this.byTime);
  }

  public void execute(TaskList tasklist, Ui ui, Storage storage) {
    String deadlineString = tasklist.add(this.description, this.doneness, this.byDate, this.byTime);
    ui.print(
        String.format("I've added this deadline:\n%s\nNow you have %d tasks in the list.\n", deadlineString,
            tasklist.getSize()));
  }
}
