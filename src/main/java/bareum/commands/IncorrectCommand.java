package bareum.commands;

import bareum.BareumException;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

public class IncorrectCommand extends Command {
    public IncorrectCommand() {

    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws BareumException {
        Ui.reply("Oops! I'm sorry but I don't know what that means :(");
    }
}
