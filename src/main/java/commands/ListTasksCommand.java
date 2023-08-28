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
    if (tasks.size() == 0) {
      PrintUtility.printText("You do not have any tasks, use todo, deadline, or event to add new " +
          "ones!");
      return;
    }
    List<String> formatted = IntStream
        .range(0, tasks.size())
        .mapToObj((j) -> String.format("%d. %s", j + 1, tasks.getTask(j)))
        .collect(Collectors.toList());
    formatted.add(0, "Here are the tasks in your list:");
    String[] output = formatted.toArray(String[]::new);
    PrintUtility.printText(output);
  }
}
