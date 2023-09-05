package command;

import parser.Parser;
import tasks.TaskList;
import ui.Ui;
import enums.CommandWord;

public class NullCommand extends Command {

    public NullCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        if (!validate(super.getRawCommand())) {
            return;
        }
        Ui.showConfused();
    }

    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.NULL_COMMAND)) {
            return false;
        }

        return true;
    }
}
