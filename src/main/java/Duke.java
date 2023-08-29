import commands.Command;
import commands.CommandType;
import parser.ParseInfo;
import parser.Parser;
import storage.FileStorage;
import storage.IStorage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

public class Duke {
  private final static IStorage storage = new FileStorage();
  private final static TaskList taskList = new TaskList(storage);
  private final static Parser parser = new Parser();

  public static void main(String[] args) {
    Ui.printText("Hello! I'm Cyrus", "What can I do for you?");
    String input;
    Scanner sc = new Scanner(System.in);
    while (true) {
      input = sc.nextLine();
      ParseInfo parseInfo = parser.parse(input);

      // Handle empty inputs
      if (parseInfo == ParseInfo.EMPTY) {
        Ui.printText("Enter a command please!");
        continue;
      }

      if (parseInfo.commandType == CommandType.BYE) break;

      Command commandToRun = parser.dispatchCommand(taskList, parseInfo);
      commandToRun.run();
    }

    Ui.printText("Bye. Hope to see you again soon!");
  }
}
