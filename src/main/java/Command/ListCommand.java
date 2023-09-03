package Command;

import Tasks.TaskList;
import enums.CommandWord;

public class ListCommand extends Command {
    public ListCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        if (!validate(super.getRawCommand())) {
            System.out.println("No Task was listed.");
            return;
        }
        taskList.listAllTasks();
    }

    protected boolean validate(String rawCommand) {
        String[] args = getArgs(rawCommand);
        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.LIST)) {
            return false;
        }

        return args.length == 1;
    }

}
