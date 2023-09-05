package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;
import ui.Ui;

public class NullCommand extends Command {

    public NullCommand(String rawCommand) {
        super(rawCommand);
    }

    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);

        return CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.NULL_COMMAND);
    }

    public void execute(TaskList taskList) {
        if (!validate(super.getRawCommand())) {
            return;
        }
        Ui.showConfused();
    }
}
