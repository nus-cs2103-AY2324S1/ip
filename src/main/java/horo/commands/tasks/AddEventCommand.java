package horo.commands.tasks;

import java.util.regex.MatchResult;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.tasks.Event;
import horo.data.tasks.TaskList;

/**
 * AddEventCommand
 */
public class AddEventCommand extends Command {

  public static final String DISPLAY_FORMAT = "event <description> /from yyyy/mm/dd HH:mm /to yyyy/mm/dd HH:mm";
  private static final String NAME = "event";
  private static final String REGEX = "^event ([\\w ]+) \\/from (\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}) \\/to (\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2})";

  private String description;
  private String from;
  private String to;

  public AddEventCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    MatchResult m = validateAndParse(input);
    description = m.group(1);
    from = m.group(2);
    to = m.group(3);
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    TaskList taskList = storage.getTaskList();
    taskList.add(new Event(description, from, to));
    storage.updateTaskData(taskList);

    ui.horoOutput("New Event added");
  }
}
