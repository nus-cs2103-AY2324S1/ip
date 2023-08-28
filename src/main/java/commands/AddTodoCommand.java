package commands;

import tasks.TaskList;
import tasks.ToDo;
import utility.PrintUtility;

import java.util.List;

public class AddTodoCommand implements ICommand {
  @Override
  public void execute(List<String> parts) {
    TaskList tasks = TaskList.getInstance();

    // TODO: Handle this with error instead
    if (parts.size() == 1) {
      PrintUtility.printText("ToDo is missing a body!");
      return;
    }
    String content = String.join(" ", parts.subList(1, parts.size()));
    ToDo todo = new ToDo(content);
    tasks.addTask(todo);
    PrintUtility.printAddTask(todo);
  }
}
