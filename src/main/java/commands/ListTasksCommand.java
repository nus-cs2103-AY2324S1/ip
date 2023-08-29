package commands;

import parser.ParseInfo;
import tasks.TaskList;
import ui.Ui;

public class ListTasksCommand extends Command {
  public ListTasksCommand(TaskList taskList, ParseInfo parseInfo) {
    super(taskList, parseInfo);
  }

  @Override
  public void execute() {
    Ui.printText(this.taskList.toString());
  }
}
