package horo.commands.tasks;

import java.util.regex.MatchResult;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.tasks.Deadline;
import horo.data.tasks.TaskList;

/**
 * AddDeadlineCommand
 */
public class AddDeadlineCommand extends Command {
  private static final String NAME = "deadline";
  private static final String REGEX = "^deadline ([\\w ]+) \\/by (\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2})";
  private static final String DISPLAY_FORMAT = "deadline <description> /by yyyy/mm/dd HH:mm";

  private String description;
  private String by;

  public AddDeadlineCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    MatchResult m = validateAndParse(input);
    description = m.group(1);
    by = m.group(2);
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    TaskList taskList = storage.getTaskList();
    taskList.add(new Deadline(description, by));
    storage.updateTaskData(taskList);

    ui.horoOutput("New Deadline added");
  }
}
