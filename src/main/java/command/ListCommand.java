package command;

import parser.Parser;
import tasks.TaskList;
import enums.CommandWord;

public class ListCommand extends Command {
    public ListCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        if (!validate(super.getRawCommand())) {
            return;
        }
        taskList.listAllTasks();
    }

    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 1) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.LIST)) {
            return false;
        }

        return true;
    }

}
