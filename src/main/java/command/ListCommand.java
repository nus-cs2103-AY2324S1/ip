package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;

public class ListCommand extends Command {
    public ListCommand(String rawCommand) {
        super(rawCommand);
    }

    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 1) {
            return false;
        }

        return CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.LIST);
    }

    public void execute(TaskList taskList) {
        if (!validate(super.getRawCommand())) {
            return;
        }
        taskList.listAllTasks();
    }

}
