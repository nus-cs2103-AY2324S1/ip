package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String rawCommand) {
        super(rawCommand);
    }

    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 1) {
            return false;
        }

        return CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.BYE);
    }

    public void execute(TaskList taskList) {
        if (!validate(super.getRawCommand())) {
            return;
        }
        Ui.showByeUser();
    }
}
