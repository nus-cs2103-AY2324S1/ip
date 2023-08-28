package commands;

import tasks.TaskList;
import utility.PrintUtility;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListTasksCommand implements ICommand {
  @Override
  public void execute(List<String> parts) {
    TaskList tasks = TaskList.getInstance();
    PrintUtility.printText(tasks.toString());
  }
}
