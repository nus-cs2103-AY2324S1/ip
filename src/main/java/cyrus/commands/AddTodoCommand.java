package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;
import cyrus.tasks.ToDo;
import cyrus.ui.Ui;

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
