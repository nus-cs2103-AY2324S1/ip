package cyrus;

import java.util.Scanner;

import cyrus.commands.Command;
import cyrus.commands.CommandType;
import cyrus.parser.ParseInfo;
import cyrus.parser.Parser;
import cyrus.storage.FileStorage;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;


/**
 * Entry point of Cyrus interface.
 */
public class Duke {
    private static final IStorage STORAGE = new FileStorage("data/data.json");
    private static final TaskList TASK_LIST = new TaskList(STORAGE);
    private static final Parser PARSER = new Parser();

    public static void main(String[] args) {
        Ui.printText("Hello! I'm Cyrus", "What can I do for you?");
        String input;
        Scanner sc = new Scanner(System.in);
        while (true) {
            input = sc.nextLine();
            ParseInfo parseInfo = PARSER.parse(input);

            // Handle empty inputs
            if (parseInfo.equals(ParseInfo.EMPTY)) {
                Ui.printText("Enter a command please!");
                continue;
            }

            if (parseInfo.getCommandType() == CommandType.BYE) {
                break;
            }

            Command commandToRun = PARSER.dispatchCommand(TASK_LIST, parseInfo);
            commandToRun.run();
        }

        Ui.printText("Bye. Hope to see you again soon!");
    }
}
