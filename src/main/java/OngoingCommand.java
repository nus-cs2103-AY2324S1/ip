import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OngoingCommand extends Command {
  
  public OngoingCommand(Map<String, Object> args) {
    super("ongoing", args);
  }

  @Override
  public List<CommandElement> getCommandElements() {
    return List.of(new DateArgument("date"));
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    LocalDate date = (LocalDate) this.args.get("date");
    TaskList filteredEvents = new TaskList();
    for (Task task : tasks) {
      if ((task instanceof Event) && ((Event) task).isOngoing(date)) {
        filteredEvents.add(task);
      }
    }
    ui.print(String.format("Here are the ongoing events on %s:\n%s", ui.stringifyDate(date), ui.stringifyList(filteredEvents)));
  }

  @Override
  public boolean isExit() {
    return false;
  }

}