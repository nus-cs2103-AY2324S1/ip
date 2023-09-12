package horo.commands.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.MatchResult;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.tasks.Task;
import horo.data.tasks.TaskList;

public class FindCommand extends Command {
  private static final String NAME = "find";
  private static final String REGEX = "^find ([\\w ]+)";
  private static final String DISPLAY_FORMAT = "find <query>";

  private String query;

  public FindCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    MatchResult m = validateAndParse(input);
    query = m.group(1);
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    TaskList taskList = storage.getTaskList();
    ArrayList<Task> matchedTasks = taskList.findTasks(Arrays.asList(query.split(" ")));

    if (matchedTasks.isEmpty()) {
      ui.horoOutput("No tasks found");
      return;
    }

    StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");

    for (int i = 0; i < matchedTasks.size(); i++) {
      sb.append((i + 1) + ". " + matchedTasks.get(i) + "\n");
    }

    ui.horoOutput(sb.toString());
  }
}
