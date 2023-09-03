package Command;

import Tasks.TaskList;
import Ui.Ui;
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

    protected boolean validate(String rawCommand) {
        String[] args = getArgs(rawCommand);

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.NULLCOMMAND)) {
            System.out.println("ERROR?");
            return false;
        }

        return args.length == 1;
    }
}
