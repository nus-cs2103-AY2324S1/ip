package commands;

import utility.PrintUtility;

import java.util.List;

public class UnknownCommand implements ICommand {
  @Override
  public void execute(List<String> parts) {
    PrintUtility.printText("I'm sorry, I don't know what that means :(");
  }
}
