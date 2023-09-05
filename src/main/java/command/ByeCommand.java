package command;

import parser.Parser;
import tasks.TaskList;
import ui.Ui;
import enums.CommandWord;

public class ByeCommand extends Command {

    public ByeCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        if (!validate(super.getRawCommand())) {
            return;
        }
        Ui.showByeUser();
    }

    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 1) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.BYE)) {
            return false;
        }

        return true;
    }
}
