package commands;

import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import utility.PrintUtility;

import java.util.List;

public class AddEventCommand implements ICommand {
  @Override
  public void execute(List<String> parts) {
    TaskList tasks = TaskList.getInstance();

    if (parts.size() == 1) {
      PrintUtility.printText("Event is missing a body!");
      return;
    }

    int fromIdx = parts.indexOf("/from");
    if (fromIdx == -1) {
      PrintUtility.printText("Invalid event format: missing /from");
      return;
    }

    int toIdx = parts.indexOf("/to");
    if (toIdx == -1) {
      PrintUtility.printText("Invalid event format: missing /to");
      return;
    }

    if (toIdx < fromIdx) {
      PrintUtility.printText("/from should come before /to");
      return;
    }

    String eventName = String.join(" ", parts.subList(1, fromIdx));
    String eventFrom = String.join(" ", parts.subList(fromIdx + 1, toIdx));
    String eventTo = String.join(" ", parts.subList(toIdx + 1, parts.size()));

    Task event = new Event(eventName, eventFrom, eventTo);
    tasks.addTask(event);
    PrintUtility.printAddTask(event);
  }
}
