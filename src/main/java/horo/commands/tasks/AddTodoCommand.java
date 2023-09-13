package horo.commands.tasks;

import java.util.regex.MatchResult;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.tasks.TaskList;
import horo.data.tasks.Todo;

public class AddTodoCommand extends Command {

  public static final String DISPLAY_FORMAT = "todo <description>";
  private static final String NAME = "todo";
  private static final String REGEX = "^todo ([\\w ]+)";

  private String description;

  public AddTodoCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    MatchResult m = validateAndParse(input);
    description = m.group(1);
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    TaskList taskList = storage.getTaskList();
    taskList.add(new Todo(description));
    storage.updateTaskData(taskList);

    ui.horoOutput("New Todo added");
  }
}
