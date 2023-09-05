package command;

import parser.Parser;
import tasks.TaskList;
import enums.CommandWord;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        if (!validate(rawCommand, taskList)) {
            return;
        }
        String[] args = Parser.getArgs(rawCommand);
        String taskIndex = args[1];
        taskList.markTaskUndone(taskIndex);
    }

    public static boolean validate(String rawCommand, TaskList taskList) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 2) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.UNMARK)) {
            return false;
        }

        return taskList.validateTaskIndex(args[1]);
    }

}
