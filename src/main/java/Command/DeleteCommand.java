package Command;

import Tasks.TaskList;
import enums.CommandWord;

public class DeleteCommand extends Command {
    public DeleteCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        if (!validate(rawCommand, taskList)) {
            return;
        }
        String[] args = getArgs(rawCommand);
        taskList.deleteTask(args[1]);
    }

    protected boolean validate(String rawCommand, TaskList taskList) {
        String[] args = getArgs(rawCommand);
        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.DELETE)) {
            return false;
        }

        if (args.length != 2) {
            return false;
        }

        return taskList.validateTaskIndex(args[1]);
    }

}
