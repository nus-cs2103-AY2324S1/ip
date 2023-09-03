package Command;

import Tasks.TaskList;
import enums.CommandWord;

public class MarkCommand extends Command {
    public MarkCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        if (!validate(rawCommand, taskList)) {
            return;
        }
        String[] args = getArgs(rawCommand);
        String taskIndex = args[1];
        taskList.markTaskDone(taskIndex);
    }

    protected boolean validate(String rawCommand, TaskList taskList) {
        String[] args = getArgs(rawCommand);
        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.MARK)) {
            return false;
        }

        if (args.length != 2) {
            return false;
        }

        return taskList.validateTaskIndex(args[1]);
    }

}
