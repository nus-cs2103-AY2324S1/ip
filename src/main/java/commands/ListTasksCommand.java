package commands;

import tasks.TaskList;
import utility.PrintUtility;

import java.util.List;

public class ListTasksCommand implements ICommand {
  @Override
  public void execute(List<String> parts) {
    TaskList tasks = TaskList.getInstance();
    PrintUtility.printText(tasks.toString());
  }
}
