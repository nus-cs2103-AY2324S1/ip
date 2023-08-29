package commands;

import parser.ParseInfo;
import tasks.TaskList;

public class UnknownCommand extends Command {
  public UnknownCommand(TaskList taskList, ParseInfo parseInfo) {
    super(taskList, parseInfo);
  }

  @Override
  public void execute() throws CommandError {
    throw new CommandError("I'm sorry, I don't know what that means :(");
  }
}
