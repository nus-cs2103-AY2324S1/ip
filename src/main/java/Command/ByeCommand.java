package Command;

import Tasks.TaskList;
import Ui.Ui;
import enums.CommandWord;

public class ByeCommand extends Command {

    public ByeCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        if (!validate(super.getRawCommand())) {
            return;
        }
        Ui.showByeUser();
    }

    protected boolean validate(String rawCommand) {
        String[] args = getArgs(rawCommand);

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.BYE)) {
            System.out.println("No Bye Bye");
            return false;
        }

        return args.length == 1;
    }
}
