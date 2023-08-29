package commands;

import parser.ParseInfo;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

public class AddTodoCommand extends Command {
  public AddTodoCommand(TaskList taskList, ParseInfo parseInfo) {
    super(taskList, parseInfo);
  }

  @Override
  public void execute() throws CommandError {
    if (this.parseInfo.hasNoArgument()) throw new CommandError("ToDo is missing a body!");
    String content = String.join(" ", this.parseInfo.getArgument());
    ToDo todo = new ToDo(content);
    this.taskList.addTask(todo);
    Ui.printAddTask(todo, this.taskList.size());
  }
}
