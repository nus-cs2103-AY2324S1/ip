package horo.commands;

import java.util.regex.MatchResult;
import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.data.TaskList;
import horo.data.Todo;

public class AddTodoCommand extends Command {
  private static final String NAME = "todo";
  private static final String REGEX = "^todo ([\\w ]+)";
  private static final String DISPLAY_FORMAT = "todo <description>";

  private String description;

  public AddTodoCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    MatchResult m = validateAndParse(input);
    description = m.group(1);
  }

  public void execute(TaskList taskList, Ui ui, Storage storage) throws HoroException {
    taskList.addTask(new Todo(description));
    storage.updateTaskData(taskList);

    ui.horoOutput("New Todo added");
  }
}
